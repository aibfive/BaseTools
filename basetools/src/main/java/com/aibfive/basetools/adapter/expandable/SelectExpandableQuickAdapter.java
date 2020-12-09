/*
package com.aibfive.basetools.adapter.expandable;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SelectExpandableQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder>  extends ExpandableQuickAdapter<T , K> {

    private boolean mGroupSelectMode = false;  //组选择模式；
    private boolean mChildSelectMode = false;  //子选择模式；
    private boolean mGrandsonSelectMode = false;  //孙选择模式；
    protected OnSelectListener mSelectListener;
    protected OnSelectItemListener mSelectItemListener;
    private final String payload = "edit";
    private boolean isRecordSelect = true;
    private HashMap<Object, Boolean> selectHashMap = new HashMap<>();  //记录选择

    public SelectExpandableQuickAdapter(List<T> data, int groupLayoutId, int childLayoutId) {
        super(data, groupLayoutId, childLayoutId);
    }

    public SelectExpandableQuickAdapter(List<T> data, int groupLayoutId, int childLayoutId, int grandsonLayoutId) {
        super(data, groupLayoutId, childLayoutId, grandsonLayoutId);
    }

    @Override
    public void convertGroup(K holder, T group, boolean isExpanded, int groupPosition) {
        reigsterGroupItemClickListener(holder.itemView, holder, group, groupPosition);
    }

    public void reigsterGroupItemClickListener(View itemView, final K holder, final T group, int groupPosition){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGroupSelectMode()){
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
                        if(getOnItemClickListener() != null) {
                            setOnItemClick(v, holder.getAdapterPosition());
                        }
                    }
                }
            }
        });
    }

    @Override
    public void convertChild(K holder, MultiItemEntity childEntity, int groupPosition, int childPosition, boolean isLastChild) {
        reigsterChildItemClickListener(holder.itemView, holder, childEntity, groupPosition, childPosition);
    }

    public void reigsterChildItemClickListener(View itemView, final K holder, final MultiItemEntity childEntity, final int groupPosition, final int childPosition){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChildSelectMode()){
                    setChildSelectStatus(getData().get(groupPosition), childEntity, childPosition);
                }else{
                    if(childExpandable) {
                        int pos = holder.getAdapterPosition();
                        if(childEntity instanceof IExpandable){
                            if (((IExpandable) childEntity).isExpanded()) {
                                collapse(pos, false);
                            } else {
                                expand(pos, false);
                            }
                        }else{
                            if(getOnItemClickListener() != null) {
                                setOnItemClick(v, holder.getAdapterPosition());
                            }
                        }
                    }else{
                        if(getOnItemClickListener() != null) {
                            setOnItemClick(v, holder.getAdapterPosition());
                        }
                    }
                }
            }
        });
    }

    @Override
    public void convertGrandson(K holder, MultiItemEntity grandsonEntity, int groupPosition, int childPosition, int grandsonPosition, boolean isLastGrandson) {
        reigsterGrandsonItemClickListener(holder.itemView, holder, grandsonEntity, groupPosition, childPosition, grandsonPosition);
    }

    public void reigsterGrandsonItemClickListener(View itemView, final K holder, final MultiItemEntity grandsonEntity, final int groupPosition, final int childPosition, final int grandsonPosition ){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGrandsonSelectMode()){
                    setGrandsonSelectStatus(getData().get(groupPosition), getData().get(childPosition), grandsonEntity, grandsonPosition);
                }else{
                    if(getOnItemClickListener() != null) {
                        setOnItemClick(holder.itemView, childPosition);
                    }
                }
            }
        });
    }

    @Override
    public void replaceData(@NonNull Collection<? extends T> data) {
        if(isRecordSelect()) {
            //setOldSelectStatus(data);
            whileSetOldSelectStatus(data);
        }
        super.replaceData(data);
        if(isRecordSelect()){
            refreshOnSelectListener();
        }else{
            resetOnSelectListener();
        }
    }

    public boolean isRecordSelect() {
        return isRecordSelect;
    }

    public void setRecordSelect(boolean recordSelect) {
        isRecordSelect = recordSelect;
    }

    private void whileSetOldSelectStatus(@NonNull Collection<? extends T> data){
        for(T group : data){
            if(group != null && group instanceof TagSelectMultiItemEntity){
                TagSelectMultiItemEntity tagGroup = (TagSelectMultiItemEntity)group;
                if(selectHashMap.containsKey(tagGroup.getTag())){
                    tagGroup.setSelect(selectHashMap.get(tagGroup.getTag()));
                }
            }
            if(group != null && group instanceof IExpandable) {
                List<MultiItemEntity> childList = ((IExpandable) group).getSubItems();
                if (childList != null) {
                   whileSetOldSelectStatus((Collection<? extends T>) childList);
                }
            }
        }
    }

    */
/**
     * 设置列表之前关闭或者展开的状态
     *//*

    public void setOldSelectStatus(Collection<? extends T> data){
        if(data == null || selectHashMap.isEmpty()){
            return;
        }
        for(T group : data){
            if(group != null && group instanceof TagSelectMultiItemEntity){
                TagSelectMultiItemEntity tagGroup = (TagSelectMultiItemEntity)group;
                if(selectHashMap.containsKey(tagGroup.getTag())){
                    tagGroup.setSelect(selectHashMap.get(tagGroup.getTag()));
                }
            }
            if(group != null && group instanceof IExpandable) {
                List<MultiItemEntity> childList = ((IExpandable) group).getSubItems();
                if (childList != null) {
                    for (MultiItemEntity child : childList) {
                        if (child != null && child instanceof TagSelectMultiItemEntity) {
                            TagSelectMultiItemEntity tagChild = (TagSelectMultiItemEntity) child;
                            if (selectHashMap.containsKey(tagChild.getTag())) {
                                tagChild.setSelect(selectHashMap.get(tagChild.getTag()));
                            }
                        }
                        if (child != null && child instanceof IExpandable) {
                            List<MultiItemEntity> grandsonList = ((IExpandable) child).getSubItems();
                            if (grandsonList != null) {
                                for (MultiItemEntity grandson : grandsonList) {
                                    if (grandson != null && grandson instanceof TagSelectMultiItemEntity) {
                                        TagSelectMultiItemEntity tagGrandson = (TagSelectMultiItemEntity) grandson;
                                        if (selectHashMap.containsKey(tagGrandson.getTag())) {
                                            tagGrandson.setSelect(selectHashMap.get(tagGrandson.getTag()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void addData(@NonNull Collection<? extends T> newData) {
        super.addData(newData);
        refreshOnSelectListener();
    }

    */
/**
     * 重置
     *//*

    public void resetOnSelectListener(){
        if(mSelectListener != null){
            mSelectListener.onSelect(0, false);
        }
    }

    public void refreshOnSelectListener(){
        if(mSelectListener != null){
            int selectedCount = getSelectedCount();
            mSelectListener.onSelect(selectedCount, isSelectedAll(selectedCount, getSelectableCount()));
        }
    }

    */
/**
     * 组内子内所有孙项是否全选
     * @param child
     * @return
     *//*

    public boolean isGroupChildGrandsonAllSelected(MultiItemEntity child){
        if(child != null && isGrandsonSelectMode()) {
            List<MultiItemEntity> grandsonList = ((IExpandable)child).getSubItems();
            int selectedCount = 0;
            int selectAbleCount = 0;
            if(grandsonList != null) {
                for (MultiItemEntity grandson : grandsonList) {
                    if (grandson != null && grandson instanceof SelectMultiItemEntity) {
                        if (((SelectMultiItemEntity) grandson).isSelectAble()) {
                            selectAbleCount++;
                            if (((SelectMultiItemEntity) grandson).isSelect()) {
                                selectedCount++;
                            }
                        }
                    }
                }
            }else{
                return false;
            }
            return selectedCount == selectAbleCount;
        }else{
            return false;
        }
    }

    */
/**
     * 组内所有子项是否全选
     * @param group
     * @return
     *//*

    public boolean isGroupChildAllSelected(MultiItemEntity group){
        if(group != null && isChildSelectMode()) {
            List<MultiItemEntity> childList = ((IExpandable)group).getSubItems();
            int selectedCount = 0;
            int selectAbleCount = 0;
            if(childList != null) {
                for (MultiItemEntity child : childList) {
                    if (child != null && child instanceof SelectMultiItemEntity) {
                        if (((SelectMultiItemEntity) child).isSelectAble()) {
                            selectAbleCount++;
                            if (((SelectMultiItemEntity) child).isSelect()) {
                                selectedCount++;
                            }
                        }
                    }
                }
            }else{
                return false;
            }
            return selectedCount == selectAbleCount;
        }else{
            return false;
        }
    }

    */
/**
     * 获取选中数量
     * @return
     *//*

    public int getSelectedCount(){
        int count = 0;
        if(isGrandsonSelectMode()){
            for(T group : mGroupList){
                if(group != null) {
                    List<MultiItemEntity> childList = ((IExpandable) group).getSubItems();
                    if(childList != null) {
                        for (MultiItemEntity child : childList) {
                            if (child != null) {
                                List<MultiItemEntity> grandsonList = ((IExpandable) child).getSubItems();
                                if(grandsonList != null) {
                                    for (MultiItemEntity grandson : grandsonList) {
                                        if (grandson != null && grandson instanceof SelectMultiItemEntity) {
                                            if (((SelectMultiItemEntity) grandson).isSelectAble() && ((SelectMultiItemEntity) grandson).isSelect()) {
                                                count++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else if(isChildSelectMode()){
            for(T group : mGroupList){
                if(group != null) {
                    List<MultiItemEntity> childList = ((IExpandable) group).getSubItems();
                    if(childList != null) {
                        for (MultiItemEntity child : childList) {
                            if (child != null && child instanceof SelectMultiItemEntity) {
                                if (((SelectMultiItemEntity) child).isSelectAble() && ((SelectMultiItemEntity) child).isSelect()) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }else if(isGroupSelectMode()){
            for(T group : mGroupList){
                if(group != null && group instanceof SelectMultiItemEntity){
                    if (((SelectMultiItemEntity)group).isSelectAble() && ((SelectMultiItemEntity)group).isSelect()) {
                        count++;
                    }
                }
            }
            return count;
        }
        return count;
    }

    */
/**
     * 获取可选择数量
     * @return
     *//*

    public int getSelectableCount(){
        if(isGrandsonSelectMode()){
            int count = 0;
            for(T group : mGroupList){
                if(group != null) {
                    List<MultiItemEntity> childList = ((IExpandable) group).getSubItems();
                    if(childList != null) {
                        for (MultiItemEntity child : childList) {
                            if (child != null) {
                                List<MultiItemEntity> grandsonList = ((IExpandable) child).getSubItems();
                                if(grandsonList != null) {
                                    for (MultiItemEntity grandson : grandsonList) {
                                        if (grandson != null && grandson instanceof SelectMultiItemEntity) {
                                            if (((SelectMultiItemEntity) grandson).isSelectAble()) {
                                                count++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return count;

        }else if(isChildSelectMode()){
            int count = 0;
            for(T group : mGroupList){
                if(group != null) {
                    List<MultiItemEntity> childList = ((IExpandable) group).getSubItems();
                    if(childList != null) {
                        for (MultiItemEntity child : childList) {
                            if (child != null && child instanceof SelectMultiItemEntity) {
                                if (((SelectMultiItemEntity) child).isSelectAble()) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
            return count;
        }else if(isGroupSelectMode()){
            int count = 0;
            for(T group : mGroupList){
                if(group != null && group instanceof SelectMultiItemEntity) {
                    if (((SelectMultiItemEntity) group).isSelectAble()) {
                        count++;
                    }
                }
            }
            return count;
        }
        return 0;
    }

    public boolean isSelectedAll(){
        int selectableCount = getSelectableCount();
        int selectedCount = getSelectedCount();
        if(selectedCount == 0 || selectableCount == 0){
            return false;
        }
        return selectedCount == selectableCount;
    }

    public boolean isSelectedAll(int selectedCount, int selectableCount){
        if(selectedCount == 0 || selectableCount == 0){
            return false;
        }
        return selectedCount == selectableCount;
    }

    */
/**
     * 设置所有选择状态
     *//*

    public void setAllSelectStatus(){
        boolean isSelectedAll = isSelectedAll();
        if(isGroupSelectMode() && !isChildSelectMode() && !isGrandsonSelectMode()){  //仅开启组选
            setAllGroupStatus(isSelectedAll);
        }else if(!isGroupSelectMode() && isChildSelectMode() && !isGrandsonSelectMode()){  //仅开启子选
            setAllChildStatus(isSelectedAll);
        }else if(!isGroupSelectMode() && !isChildSelectMode() && isGrandsonSelectMode()){  //仅开启孙选
            setAllGrandsonStatus(isSelectedAll);
        }else if(isGroupSelectMode() && isChildSelectMode() && isGrandsonSelectMode()){  //开启组选，子选，孙选
            setAllGroupStatus(isSelectedAll);
            setAllChildStatus(isSelectedAll);
            setAllGrandsonStatus(isSelectedAll);
        }else if(isGroupSelectMode() && isChildSelectMode() && !isGrandsonSelectMode()){  //开启组选，子选
            setAllGroupStatus(isSelectedAll);
            setAllChildStatus(isSelectedAll);
        }else if(!isGroupSelectMode() && isChildSelectMode() && isGrandsonSelectMode()){  //开启子选，孙选
            setAllChildStatus(isSelectedAll);
            setAllGrandsonStatus(isSelectedAll);
        }
        refreshOnSelectListener();
        //notifyDataSetChanged();
    }

    */
/**
     * 选择全部子项
     *//*

    private void setAllChildStatus(boolean select){
        for(T group : mGroupList){
            if(group != null) {
                List<MultiItemEntity> childList = ((IExpandable) group).getSubItems();
                if(childList != null) {
                    for (MultiItemEntity child : childList) {
                        if (child != null && child instanceof SelectMultiItemEntity) {
                            SelectMultiItemEntity selectEntity = ((SelectMultiItemEntity) child);
                            if (selectEntity.isSelectAble()) {
                                selectEntity.setSelect(!select);
                                recordSelect((T) selectEntity);
                                notifyItemChangedSelect(selectEntity);
                            }
                        }
                    }
                }
            }
        }
    }

    */
/**
     * 选择全部孙项
     *//*

    private void setAllGrandsonStatus(boolean select){
        for(T group : mGroupList){
            if(group != null) {
                List<MultiItemEntity> childList = ((IExpandable) group).getSubItems();
                if(childList != null) {
                    for (MultiItemEntity child : childList) {
                        if (child != null) {
                            List<MultiItemEntity> grandsonList = ((IExpandable) child).getSubItems();
                            if(grandsonList != null) {
                                for (MultiItemEntity grandson : grandsonList) {
                                    if (grandson != null && grandson instanceof SelectMultiItemEntity) {
                                        SelectMultiItemEntity selectEntity = ((SelectMultiItemEntity) grandson);
                                        if (selectEntity.isSelectAble()) {
                                            selectEntity.setSelect(!select);
                                            recordSelect((T) selectEntity);
                                            notifyItemChangedSelect(selectEntity);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    */
/**
     * 选择全部组项
     *//*

    private void setAllGroupStatus(boolean select){
        for(T group : mGroupList){
            if(group != null && group instanceof SelectMultiItemEntity){
                SelectMultiItemEntity selectEntity = ((SelectMultiItemEntity)group);
                if(selectEntity.isSelectAble()) {
                    selectEntity.setSelect(!select);
                    recordSelect((T) selectEntity);
                    notifyItemChangedSelect(selectEntity);
                }
            }
        }
    }

    */
/**
     * 设置组选择状态
     * @param groupEntity
     *//*

    public void setGroupSelectStatus(MultiItemEntity groupEntity){
        if(isGroupSelectMode()) {
            if (groupEntity != null && groupEntity instanceof SelectMultiItemEntity) {
                SelectMultiItemEntity group = ((SelectMultiItemEntity) groupEntity);
                if (group.isSelectAble()) {
                    group.setSelect(!group.isSelect());
                    recordSelect((T) group);
                    notifyItemChangedSelect(groupEntity);
                    if (isChildSelectMode()) {
                        List<MultiItemEntity> childList = ((IExpandable) groupEntity).getSubItems();
                        if(childList != null) {
                            for (MultiItemEntity childEntity : childList) {
                                if (childEntity != null && childEntity instanceof SelectMultiItemEntity) {
                                    SelectMultiItemEntity child = ((SelectMultiItemEntity) childEntity);
                                    if (child.isSelectAble()) {
                                        child.setSelect(group.isSelect());
                                        recordSelect((T) child);
                                        notifyItemChangedSelect(childEntity);
                                        if (isGrandsonSelectMode()) {
                                            List<MultiItemEntity> grandsonList = ((IExpandable) child).getSubItems();
                                            if(grandsonList != null) {
                                                for (MultiItemEntity grandsonEntity : grandsonList) {
                                                    if (grandsonEntity != null && grandsonEntity instanceof SelectMultiItemEntity) {
                                                        SelectMultiItemEntity grandson = ((SelectMultiItemEntity) grandsonEntity);
                                                        if (grandson.isSelectAble()) {
                                                            grandson.setSelect(group.isSelect());
                                                            recordSelect((T) grandson);
                                                            notifyItemChangedSelect(grandsonEntity);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            refreshOnSelectListener();
            if (mSelectItemListener != null) {
                mSelectItemListener.onSelectItem(getData().indexOf(groupEntity));
            }
        }
    }

    public void notifyItemChangedSelect(MultiItemEntity allEntity){
        List<T> dataList = getData();
        if(dataList.contains(allEntity)){
            notifyItemChanged(dataList.indexOf(allEntity), payload);
        }
    }

    public void notifyItemChangedSelect(int position){
        notifyItemChanged(position, payload);
    }

    public void setChildSelectStatus(MultiItemEntity group, MultiItemEntity child, int childPosition){
        if(isChildSelectMode()) {
            if (child != null && child instanceof SelectMultiItemEntity) {
                SelectMultiItemEntity childEntity = (SelectMultiItemEntity) child;
                if(childEntity.isSelectAble()) {
                    childEntity.setSelect(!childEntity.isSelect());
                    recordSelect((T) childEntity);
                    notifyItemChangedSelect(childPosition);
                }
            }

            */
/**
             * 若开启了孙项模式，
             *//*

            if(isGrandsonSelectMode()){
                if(child != null && child instanceof IExpandable){
                    List<MultiItemEntity> grandsonList = ((IExpandable)child).getSubItems();
                    for(MultiItemEntity grandsonEntity : grandsonList){
                        if(grandsonEntity != null && grandsonEntity instanceof SelectMultiItemEntity){
                            SelectMultiItemEntity selectEntity = (SelectMultiItemEntity) grandsonEntity;
                            if(selectEntity.isSelectAble()) {
                                selectEntity.setSelect(((SelectMultiItemEntity) child).isSelect());
                                notifyItemChangedSelect(grandsonEntity);
                            }
                        }
                    }
                }
            }

            */
/**
             * 若开启了组项模式，根据该组子项是否全选，设置该组是否选中
             *//*

            if (isGroupSelectMode()) {
                if (group != null && group instanceof SelectMultiItemEntity) {
                    SelectMultiItemEntity selectEntity = (SelectMultiItemEntity) group;
                    if(selectEntity.isSelectAble()) {
                        boolean isGroupChildAllSelected = isGroupChildAllSelected(group);
                        selectEntity.setSelect(isGroupChildAllSelected);
                        recordSelect((T) selectEntity);
                        notifyItemChangedSelect(group);
                    }
                }

            }
            refreshOnSelectListener();
            if(mSelectItemListener != null){
                mSelectItemListener.onSelectItem(childPosition);
            }
        }
    }

    public void setGrandsonSelectStatus(MultiItemEntity groud, MultiItemEntity child, MultiItemEntity grandson, int grandsonPosition) {
        if(isGrandsonSelectMode()) {
            if (grandson != null && grandson instanceof SelectMultiItemEntity) {
                SelectMultiItemEntity selectEntity = (SelectMultiItemEntity) grandson;
                if(selectEntity.isSelectAble()) {
                    selectEntity.setSelect(!selectEntity.isSelect());
                    recordSelect((T) selectEntity);
                    notifyItemChangedSelect(grandsonPosition);
                }
            }

            */
/**
             * 若开启了子项模式，根据该组孙项是否全选，设置该子组是否选中
             *//*

            if(isChildSelectMode()){
                if (child != null && child instanceof SelectMultiItemEntity) {
                    SelectMultiItemEntity selectEntity = (SelectMultiItemEntity) child;
                    if(selectEntity.isSelectAble()) {
                        boolean isGroupChildGrandsonAllSelected = isGroupChildGrandsonAllSelected(child);
                        selectEntity.setSelect(isGroupChildGrandsonAllSelected);
                        recordSelect((T) selectEntity);
                        notifyItemChangedSelect(child);
                    }
                }
            }


            */
/**
             * 若开启了组项模式，根据该组子项是否全选，设置该组是否选中
             *//*

            if (isGroupSelectMode()) {
                if (groud != null && groud instanceof SelectMultiItemEntity) {
                    SelectMultiItemEntity selectEntity = (SelectMultiItemEntity) groud;
                    if(selectEntity.isSelectAble()) {
                        boolean isGroupChildAllSelected = isGroupChildAllSelected(groud);
                        selectEntity.setSelect(isGroupChildAllSelected);
                        recordSelect((T) selectEntity);
                        notifyItemChangedSelect(groud);
                    }
                }
            }
            refreshOnSelectListener();
            if(mSelectItemListener != null){
                mSelectItemListener.onSelectItem(grandsonPosition);
            }
        }
    }

    */
/**
     * 是否组选择
     * @return
     *//*

    public boolean isGroupSelectMode() {
        return mGroupSelectMode;
    }

    */
/**
     * 设置组选择
     * @param groupSelectMode
     *//*

    public void setGroupSelectMode(boolean groupSelectMode) {
        this.mGroupSelectMode = groupSelectMode;
    }

    */
/**
     * 是否子选择
     * @return
     *//*

    public boolean isChildSelectMode() {
        return mChildSelectMode;
    }

    */
/**
     * 设置子选择模式
     * @param childSelectMode
     *//*

    public void setChildSelectMode(boolean childSelectMode) {
        this.mChildSelectMode = childSelectMode;
    }

    */
/**
     * 是否孙选择
     * @return
     *//*

    public boolean isGrandsonSelectMode() {
        return mGrandsonSelectMode;
    }

    */
/**
     * 是否孙选择
     * @param mGrandsonSelectMode
     *//*

    public void setGrandsonSelectMode(boolean mGrandsonSelectMode) {
        this.mGrandsonSelectMode = mGrandsonSelectMode;
    }

    public interface OnSelectListener{
        void onSelect(int selectedCount, boolean selectedAll);
    }

    public void setOnSelectListener(OnSelectListener listener){
        this.mSelectListener = listener;
    }

    public interface OnSelectItemListener{
        void onSelectItem(int position);
    }

    public void setOnSelectItemListener(OnSelectItemListener listener){
        this.mSelectItemListener = listener;
    }

    private void recordSelect(T t){
        try {
            if(isRecordSelect()){
                if(t instanceof TagSelectMultiItemEntity){
                    TagSelectMultiItemEntity tagSelectMultiItemEntity = (TagSelectMultiItemEntity)t;
                    selectHashMap.put((tagSelectMultiItemEntity).getTag(), tagSelectMultiItemEntity.isSelect());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
