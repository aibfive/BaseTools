package com.aibfive.basetools.adapter;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.aibfive.basetools.util.EmptyUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 支持 单选 多选功能的Adapter
 */

public abstract class SelectedAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    protected boolean selectMode = true;//总开关 开启选择功能
    protected int defSelectIndex = 0;//默认单个选中状态
    protected int curSelectIndex = 0;//当前单个选中状态
    private boolean cancelSingleSeleted = false;//是否可以单选取消
    protected boolean multiSelected;//是否多选 默认为单选
    protected ArrayList<Integer> selectList = new ArrayList<>();//多选下标集合
    private ArrayList<Integer> selectNotList = new ArrayList<>();//不能选择的下标集合


    public SelectedAdapter(@LayoutRes int mLayoutResId) {
        super(mLayoutResId);

    }


    /**
     * 设置开启模式
     *
     * @param isOpen 开启选择模式  默认单选
     */
    public void setSelectMode(boolean isOpen) {
        this.selectMode = isOpen;
        notifyDataSetChanged();
        setSelectMode(isOpen, defSelectIndex);
    }


    /**
     * @param isOpen
     * @param defaultIndex 单选的 默认选中下标
     */
    public void setSelectMode(boolean isOpen, int defaultIndex) {
        this.selectMode = isOpen;
        this.defSelectIndex = defaultIndex;
        this.curSelectIndex = this.defSelectIndex;
        notifyDataSetChanged();
    }

    /**
     * 设置开启模式
     *
     * @param isOpen        开启选择模式
     * @param multiSelected 是否多选
     */
    public void setSelectMode(boolean isOpen, boolean multiSelected) {
        this.selectMode = isOpen;
        this.multiSelected = multiSelected;
        notifyDataSetChanged();
    }

    public ArrayList<Integer> getSelectedList() {
        return selectList;
    }

    public ArrayList<T> getSelectedData(){
        ArrayList<Integer> selectedList = getSelectedList();
        ArrayList<T> selects = new ArrayList<>();
        List<T> datas = getData();
        if (EmptyUtil.isNotEmpty(selectedList)) {
            for (int i = 0; i < selectedList.size(); i++) {
                selects.add(datas.get(selectedList.get(i)));
            }
        }
        return selects;
    }


    public boolean isSelectAll(){
        int selectedCount = getSelectedData().size();
        int allCount = getItemCount();
        if(selectedCount == 0 || allCount == 0){
            return false;
        }
        return getSelectedData().size() == getItemCount();
    }

    /**
     * 默认的选择下标
     *
     * @return
     */
    public int getDefSelectedIndex() {
        return defSelectIndex;
    }

    public void setDefSelectedIndex(int index) {
        defSelectIndex = index;
        curSelectIndex = defSelectIndex;
        notifyDataSetChanged();
    }

    public void setCancelSingleSeleted(boolean cancelSingleSeleted) {
        this.cancelSingleSeleted = cancelSingleSeleted;
    }

    /**
     * 当前的选择下标
     *
     * @return
     */
    public int getSelectedIndex() {
        return curSelectIndex;
    }


    public void setSelectedIndex(int index) {
        curSelectIndex = index;
        notifyDataSetChanged();
    }

    /**
     * 当前的选择下标
     *
     * @return
     */
    public T getSelectedItem() {
        int selectedIndex = getSelectedIndex();
        if (selectedIndex != -1) {
            return getItem(selectedIndex);
        }
        return null;
    }

    public ArrayList<Integer> getSelectNotList() {
        return selectNotList;
    }

    public void setSelectNotList(ArrayList<Integer> selectNotList) {
        this.selectNotList = selectNotList;
        notifyDataSetChanged();
    }

    public void setSelectedList(ArrayList<Integer> selectedList){
        this.selectList = selectedList;
        notifyDataSetChanged();
    }

    public void addSelectNotPosition(int position) {
        if (!selectNotList.contains(position)) {
            this.selectNotList.add(position);
        }
    }

    @Override
    public void setNewData(List<T> data) {
        curSelectIndex = defSelectIndex;
        selectList.clear();
        super.setNewData(data);
    }

    public boolean isSelected(int position){
        return multiSelected ? selectList.contains(position) : curSelectIndex == position;
    }

    public void clearSelected() {
        curSelectIndex = defSelectIndex;
        selectList.clear();
        notifyDataSetChanged();
        if(onAllSelectListener != null){
            onAllSelectListener.onAllSelect(false, 0);
        }
    }

    @Override
    public void setOnItemClick(View v, int position) {
        setClickSelectState(v,position);
        super.setOnItemClick(v, position);
    }

    public void selectAll(){
        int size = mData.size();
        //设置选择器
        if (selectMode && multiSelected) {
            selectList.clear();
            for(int position = 0; position < size; position++){
                selectList.add(position);
            }
        }
        notifyDataSetChanged();
        if(onAllSelectListener != null){
            onAllSelectListener.onAllSelect(true, selectList.size());
        }
    }

    public void removeSelect(int position){
        if (selectMode && multiSelected) {
            Integer positionInt = Integer.parseInt(String.valueOf(position));
            selectList.remove(positionInt);
            notifyItemChanged(selectList.indexOf(positionInt));
            if(onAllSelectListener != null){
                onAllSelectListener.onAllSelect(true, selectList.size());
            }
        }
    }

    /**
     /**
     * 设置选择的状态 需要是可以重写方法
     *
     * @param position
     */
    protected void setClickSelectState(View v,int position) {
        //设置选择器
        if (selectMode && !selectNotList.contains(position)) {
            if (multiSelected) {
                if (selectList.contains(position)) {
                    selectList.remove((Integer) position);
                    if (onItemSelectListener != null) {
                        onItemSelectListener.onmultiSelectedCancel(v,position);
                    }
                } else {
                    selectList.add(position);
                    if (onItemSelectListener != null) {
                        onItemSelectListener.onmultiSelected(v,position);
                    }
                }
                if(onAllSelectListener != null){
                    onAllSelectListener.onAllSelect(isSelectAll(), getSelectedList().size());
                }
            } else {
                if (curSelectIndex > -1) notifyItemChanged(curSelectIndex);
                if (curSelectIndex == position && cancelSingleSeleted) {//同一位置，且开启取消选择
                    curSelectIndex = -1;
                } else {
                    curSelectIndex = position;
                }
                if (onItemSelectListener != null) {
                    onItemSelectListener.onSingleSelect(v, position);
                }
            }
            notifyItemChanged(position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (selectMode) {
            boolean isSelected = multiSelected ? selectList.contains(position) : curSelectIndex == position;//设置状态
            holder.itemView.setSelected(isSelected);
            T item = getItem(position);
            if (onItemSelectListener != null) {
                onItemSelectListener.convertSelect(holder, item, isSelected);
            }
        }
        super.onBindViewHolder(holder, position);
    }


    public void setOnSelectClickListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    private OnItemSelectListener onItemSelectListener;

    public interface OnItemSelectListener<T>{
        void onSingleSelect(View v, int position);

        void convertSelect(BaseViewHolder helper, T item, boolean isSelected);

        void onmultiSelected(View v, int position);

        void onmultiSelectedCancel(View v, int position);
    }

    public void setOnAllSelectListener(OnAllSelectListener onAllSelectListener){
        this.onAllSelectListener = onAllSelectListener;
    }

    private OnAllSelectListener onAllSelectListener;

    public interface OnAllSelectListener<T>{
        void onAllSelect(boolean isAllSelect, int selectCount);
    }


}
