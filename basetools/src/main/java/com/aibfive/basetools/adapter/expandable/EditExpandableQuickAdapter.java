package com.aibfive.basetools.adapter.expandable;

import android.support.annotation.NonNull;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.Collection;
import java.util.List;

public class EditExpandableQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends SelectExpandableQuickAdapter<T, K> {

    private OnEditListener mListener;
    private boolean mEditMode = false;  //是否可编辑
    private boolean mEditStatus = false;  //编辑状态
    private final String payload = "edit";

    public EditExpandableQuickAdapter(List data, int groupLayoutId, int childLayoutId) {
        super(data, groupLayoutId, childLayoutId);
    }

    public EditExpandableQuickAdapter(List<T> data, int groupLayoutId, int childLayoutId, int grandsonLayoutId) {
        super(data, groupLayoutId, childLayoutId, grandsonLayoutId);
    }

    @Override
    public void convertGroup(K holder, T group, boolean isExpanded, int groupPosition) {
        reigsterGroupItemClickListener(holder.itemView, holder, group, groupPosition);
    }

    /**
     * 用于为child项的View设置与group项同样的操作，除了折叠、展开操作
     * @param itemView
     * @param holder
     * @param group
     */
    public void reigsterGroupItemInChildGrandsonClickListener(View itemView, K holder, T group){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEditStatus() && isGroupSelectMode()){
                    setGroupSelectStatus(group);
                }else{
                    if(getOnItemClickListener() != null){
                        setOnItemClick(v, holder.getAdapterPosition());
                    }
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(isEditMode() && !isEditStatus()){
                    //长按的是group的时候，childPosition = -1
                    if (isGroupSelectMode()) {
                        /**
                         * 若长按组项并且组项选择模式开启
                         * 开启编辑模式
                         */
                        setEditStatus(true);
                    }
                }
                return true;
            }
        });
    }

    public void reigsterGroupItemClickListener(View itemView, K holder, T group, int groupPosition){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEditStatus() && isGroupSelectMode()){
                    setGroupSelectStatus(group);
                }else{
                    if(groupExpandable) {
                        int pos = holder.getAdapterPosition();
                        if(group instanceof IExpandable) {
                            if (((IExpandable) group).isExpanded()) {
                                collapse(pos, false);
                            } else {
                                expand(pos, false);
                            }
                        }else{
                            if(getOnItemClickListener() != null){
                                setOnItemClick(v, holder.getAdapterPosition());
                            }
                        }
                    }else{
                        if(getOnItemClickListener() != null){
                            setOnItemClick(v, holder.getAdapterPosition());
                        }
                    }
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(isEditMode() && !isEditStatus()){
                    //长按的是group的时候，childPosition = -1
                    if (isGroupSelectMode()) {
                        /**
                         * 若长按组项并且组项选择模式开启
                         * 开启编辑模式
                         */
                        setEditStatus(true);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void convertChild(K holder, MultiItemEntity childEntity, int groupPosition, int childPosition, boolean isLastChild) {
        reigsterChildItemClickListener(holder.itemView, holder, childEntity, groupPosition, childPosition);
    }

    public void reigsterChildItemClickListener(View itemView, K holder, MultiItemEntity childEntity, int groupPosition, int childPosition){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEditStatus() && isChildSelectMode()){
                    setChildSelectStatus(getData().get(groupPosition), childEntity, childPosition);
                }else {
                    if(childExpandable) {
                        int pos = holder.getAdapterPosition();
                        if(childEntity instanceof IExpandable){
                            if (((IExpandable) childEntity).isExpanded()) {
                                collapse(pos, false);
                            } else {
                                expand(pos, false);
                            }
                        }else{
                            if(getOnItemClickListener() != null){
                                setOnItemClick(v, holder.getAdapterPosition());
                            }
                        }
                    }else{
                        if(getOnItemClickListener() != null){
                            setOnItemClick(v, holder.getAdapterPosition());
                        }
                    }
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(isEditMode() && !isEditStatus()){
                    if (isChildSelectMode()) {
                        /*
                         * 或长按子项并且子项选择模式开启
                         * 开启编辑模式
                         */
                        setEditStatus(true);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void convertGrandson(K holder, MultiItemEntity grandsonEntity, int groupPosition, int childPosition, int grandsonPosition, boolean isLastGrandson) {
        reigsterGrandsonItemClickListener(holder.itemView, holder, grandsonEntity, groupPosition, childPosition, grandsonPosition);
    }

    public void reigsterGrandsonItemClickListener(View itemView, K holder, MultiItemEntity grandsonEntity, int groupPosition, int childPosition, int grandsonPosition ){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEditStatus() && isGrandsonSelectMode()){
                    setGrandsonSelectStatus(getData().get(groupPosition), getData().get(childPosition), grandsonEntity, grandsonPosition);
                }else {
                    if(getOnItemClickListener() != null) {
                        setOnItemClick(holder.itemView, childPosition);
                    }
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(isEditMode() && !isEditStatus()){
                    if (isChildSelectMode()) {
                        /*
                         * 或长按子项并且子项选择模式开启
                         * 开启编辑模式
                         */
                        setEditStatus(true);
                    }
                }
                return true;
            }
        });
    }

    /**
     * 是否可编辑
     * @return
     */
    public boolean isEditMode() {
        return mEditMode;
    }

    /**
     * 设置编辑模式
     * @param editMode
     */
    public void setEditMode(boolean editMode) {
        this.mEditMode = editMode;

    }

    /**
     * 是否编辑状态
     * @return
     */
    public boolean isEditStatus() {
        return mEditStatus;
    }

    /**
     * 设置编辑状态
     * @param editStatus
     */
    public void setEditStatus(boolean editStatus) {
        this.mEditStatus = editStatus;
        if(mListener != null){
            mListener.onEdit(mEditStatus);
        }
        //notifyDataSetChanged();
        int size = getData().size();
        for(int i = 0; i < size; i++){
            notifyItemChanged(i, payload);
        }
    }

    public void resetOnEditListener() {
        setEditStatus(false);
    }

    @Override
    public void replaceData(@NonNull Collection<? extends T> data) {
        super.replaceData(data);
        resetOnEditListener();
    }


    public interface OnEditListener{
        void onEdit(boolean edit);
    }

    public void setOnEditListener(OnEditListener listener){
        this.mListener = listener;
    }

}
