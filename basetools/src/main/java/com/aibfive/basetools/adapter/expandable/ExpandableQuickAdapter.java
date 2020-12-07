package com.aibfive.basetools.adapter.expandable;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExpandableQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T, K> {

    protected List<T> mGroupList = new ArrayList<>();
    public static final int TYPE_GROUP = 0;
    public static final int TYPE_CHILD = 1;
    public static final int TYPE_GRANDSON = 2;
    protected boolean groupExpandable = true;
    protected boolean childExpandable = false;
    private boolean isOnlyExpandOne = false;
    private HashMap<Object, Boolean> expandCollapseHashMap = new HashMap<>();  //记录展开关闭

    public ExpandableQuickAdapter(List<T> data, int groupLayoutId, int childLayoutId) {
        super(data);
        List<T> emptyList = new ArrayList<>();
        mGroupList.addAll(data == null ? emptyList : data);
        addItemType(TYPE_GROUP, groupLayoutId);
        addItemType(TYPE_CHILD, childLayoutId);

    }

    public ExpandableQuickAdapter(List<T> data, int groupLayoutId, int childLayoutId, int grandsonLayoutId) {
        super(data);
        List<T> emptyList = new ArrayList<>();
        mGroupList.addAll(data == null ? emptyList : data);
        addItemType(TYPE_GROUP, groupLayoutId);
        addItemType(TYPE_CHILD, childLayoutId);
        addItemType(TYPE_GRANDSON, grandsonLayoutId);
    }


    public void remove(Iterator groupIterator, Iterator childIterator, Iterator grandsonIterator, MultiItemEntity entity) {
        remove(groupIterator, childIterator, grandsonIterator, entity, true);
    }

    public void remove(MultiItemEntity entity) {
        remove(entity, true);
    }

    /**
     * 移除group child grandson
     * @param entity
     * @param nullRemoveParent
     */
    public void remove(MultiItemEntity entity, boolean nullRemoveParent) {
        switch (entity.getItemType()){
            case TYPE_GROUP:
                mGroupList.remove(entity);
                if(getData().contains(entity)) {
                    super.remove(getData().indexOf(entity));
                }else{
                    notifyItemRemoved(getData().indexOf(entity));
                }
                break;
            case TYPE_CHILD:
                T currentGroupInChild = null;
                for(T group : mGroupList){
                    List<MultiItemEntity> childList = ((IExpandable)group).getSubItems();
                    if(childList.contains(entity)){
                        childList.remove(entity);
                        currentGroupInChild = group;
                        if(nullRemoveParent && childList.size() == 0){
                            mGroupList.remove(group);
                        }
                        break;
                    }
                }
                if(getData().contains(entity)) {
                    int index = getData().indexOf(entity);
                    int groupPositionAtAll = getParentPositionInAll(index);
                    super.remove(index);
                    if(nullRemoveParent){
                        if (groupPositionAtAll != -1) {
                            IExpandable groupEntity = (IExpandable) getData().get(groupPositionAtAll);
                            if (!hasSubItems(groupEntity)) {
                                super.remove(groupPositionAtAll);
                                return;
                            }
                        }
                    }
                    int currentGroupPositionInChild = getData().indexOf(currentGroupInChild);
                    notifyItemRangeChanged(currentGroupPositionInChild, getData().size() - currentGroupPositionInChild);
                }else{
                    if (nullRemoveParent && !hasSubItems((IExpandable)currentGroupInChild)) {
                        super.remove(getData().indexOf(currentGroupInChild));
                    }else {
                        notifyItemChanged(getData().indexOf(currentGroupInChild));
                    }
                }
                break;
            case TYPE_GRANDSON:
                T currentGroupInGrandson = null;
                IExpandable currentChildInGrandson = null;
                loop:for(T group : mGroupList){
                    List<IExpandable> childList = ((IExpandable)group).getSubItems();
                    for(IExpandable child : childList){
                        List<MultiItemEntity> grandsonList = child.getSubItems();
                        if(grandsonList.contains(entity)){
                            grandsonList.remove(entity);
                            currentGroupInGrandson = group;
                            if(nullRemoveParent && grandsonList.size() == 0){
                                childList.remove(child);
                                currentChildInGrandson = child;
                                if(childList.size() == 0){
                                    mGroupList.remove(group);
                                }
                            }
                            break loop;
                        }
                    }
                }
                if(getData().contains(entity)) {
                    int index = getData().indexOf(entity);
                    int childPositionAtAll = getParentPositionInAll(index);
                    super.remove(index);
                    if(nullRemoveParent){
                        if (childPositionAtAll != -1) {
                            IExpandable childEntity = (IExpandable) getData().get(childPositionAtAll);
                            if (!hasSubItems(childEntity)) {
                                int groupPositionAtAll = getParentPositionInAll(childPositionAtAll);
                                super.remove(childPositionAtAll);
                                if (groupPositionAtAll != -1) {
                                    IExpandable groupEntity = (IExpandable) getData().get(groupPositionAtAll);
                                    if (!hasSubItems(groupEntity)) {
                                        super.remove(groupPositionAtAll);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    int currentGroupPositionInGrandson = getData().indexOf(currentGroupInGrandson);
                    notifyItemRangeChanged(currentGroupPositionInGrandson, getData().size() - currentGroupPositionInGrandson);
                }else{
                    if (nullRemoveParent){
                        if (getData().contains(currentChildInGrandson) && !hasSubItems(currentChildInGrandson)) {
                            super.remove(getData().indexOf(currentChildInGrandson));
                            int currentGroupPosition = getData().indexOf(currentGroupInGrandson);
                            notifyItemRangeChanged(currentGroupPosition, getData().size() - currentGroupPosition);
                        }
                        if (!hasSubItems((IExpandable)currentGroupInGrandson)) {
                            super.remove(getData().indexOf(currentGroupInGrandson));
                        }
                    } else {
                        if (getData().contains(currentChildInGrandson)){
                            notifyItemChanged(getData().indexOf(currentChildInGrandson));
                        }
                        notifyItemChanged(getData().indexOf(currentGroupInGrandson));
                    }
                }
                break;
        }
    }

    /**
     * 移除groud
     * @param groupIterator
     * @param group
     */
    public void remove(Iterator groupIterator, T group) {
        groupIterator.remove();
        if(getData().contains(group)) {
            super.remove(getData().indexOf(group));
        }else{
            notifyItemRemoved(getData().indexOf(group));
        }
    }

    /**
     * 移除child
     * @param groupIterator
     * @param childIterator
     * @param group
     * @param child
     */
    public void remove(Iterator groupIterator, Iterator childIterator, T group, MultiItemEntity child) {
        remove(groupIterator, childIterator, group, child, true);
    }


    /**
     * 移除child
     * @param groupIterator
     * @param childIterator
     * @param group
     * @param child
     * @param nullRemoveParent
     */
    public void remove(Iterator groupIterator, Iterator childIterator, T group, MultiItemEntity child, boolean nullRemoveParent) {
        childIterator.remove();
        if(nullRemoveParent && ((IExpandable)group).getSubItems().size() == 0){
            groupIterator.remove();
        }
        if(getData().contains(child)) {
            int index = getData().indexOf(child);
            int groupPositionAtAll = getParentPositionInAll(index);
            super.remove(index);
            if(nullRemoveParent){
                if (groupPositionAtAll != -1) {
                    IExpandable groupEntity = (IExpandable) getData().get(groupPositionAtAll);
                    if (!hasSubItems(groupEntity)) {
                        super.remove(groupPositionAtAll);
                        return;
                    }
                }
            }
            int currentGroupPositionInChild = getData().indexOf(group);
            notifyItemRangeChanged(currentGroupPositionInChild, getData().size() - currentGroupPositionInChild);
        }else{
            if (nullRemoveParent && !hasSubItems((IExpandable)group)) {
                super.remove(getData().indexOf(group));
            }else {
                notifyItemChanged(getData().indexOf(group));
            }
        }
    }

    /**
     * 移除grandson
     * @param groupIterator
     * @param childIterator
     * @param grandsonIterator
     * @param group
     * @param child
     * @param grandson
     */
    public void remove(Iterator groupIterator, Iterator childIterator, Iterator grandsonIterator, T group, MultiItemEntity child, MultiItemEntity grandson) {
        remove(groupIterator, childIterator, grandsonIterator, group, child, grandson, true);
    }

    /**
     * 移除grandson
     * @param groupIterator
     * @param childIterator
     * @param grandsonIterator
     * @param group
     * @param child
     * @param grandson
     * @param nullRemoveParent
     */
    public void remove(Iterator groupIterator, Iterator childIterator, Iterator grandsonIterator, T group, MultiItemEntity child, MultiItemEntity grandson, boolean nullRemoveParent) {
        grandsonIterator.remove();
        if(nullRemoveParent && ((IExpandable)group).getSubItems().size() == 0){
            childIterator.remove();
            if(((IExpandable)child).getSubItems().size() == 0){
                groupIterator.remove();
            }
        }
        if(getData().contains(grandson)) {
            int index = getData().indexOf(grandson);
            int childPositionAtAll = getParentPositionInAll(index);
            super.remove(index);
            if(nullRemoveParent){
                if (childPositionAtAll != -1) {
                    IExpandable childEntity = (IExpandable) getData().get(childPositionAtAll);
                    if (!hasSubItems(childEntity)) {
                        int groupPositionAtAll = getParentPositionInAll(childPositionAtAll);
                        super.remove(childPositionAtAll);
                        if (groupPositionAtAll != -1) {
                            IExpandable groupEntity = (IExpandable) getData().get(groupPositionAtAll);
                            if (!hasSubItems(groupEntity)) {
                                super.remove(groupPositionAtAll);
                                return;
                            }
                        }
                    }
                }
            }
            int currentGroupPositionInGrandson = getData().indexOf(group);
            notifyItemRangeChanged(currentGroupPositionInGrandson, getData().size() - currentGroupPositionInGrandson);
        }else{
            if (nullRemoveParent){
                if (getData().contains(child) && !hasSubItems((IExpandable)child)) {
                    super.remove(getData().indexOf(child));
                    int currentGroupPosition = getData().indexOf(group);
                    notifyItemRangeChanged(currentGroupPosition, getData().size() - currentGroupPosition);
                }
                if (!hasSubItems((IExpandable)group)) {
                    super.remove(getData().indexOf(group));
                }
            } else {
                if (getData().contains(child)){
                    notifyItemChanged(getData().indexOf(child));
                }
                notifyItemChanged(getData().indexOf(group));
            }
        }
    }

    /**
     * 移除group child grandson  --  移除有问题，会抛出java.util.ConcurrentModificationException
     * @param groupIterator
     * @param childIterator
     * @param grandsonIterator
     * @param entity
     * @param nullRemoveParent
     */
    public void remove(Iterator groupIterator, Iterator childIterator, Iterator grandsonIterator, MultiItemEntity entity, boolean nullRemoveParent) {
        switch (entity.getItemType()){
            case TYPE_GROUP:
                groupIterator.remove();
                if(getData().contains(entity)) {
                    super.remove(getData().indexOf(entity));
                }else{
                    notifyItemRemoved(getData().indexOf(entity));
                }
                break;
            case TYPE_CHILD:
                T currentGroupInChild = null;
                for(T group : mGroupList){
                    List<MultiItemEntity> childList = ((IExpandable)group).getSubItems();
                    if(childList.contains(entity)){
                        childIterator.remove();
                        currentGroupInChild = group;
                        if(nullRemoveParent && childList.size() == 0){
                            groupIterator.remove();
                        }
                        break;
                    }
                }
                if(getData().contains(entity)) {
                    int index = getData().indexOf(entity);
                    int groupPositionAtAll = getParentPositionInAll(index);
                    super.remove(index);
                    if(nullRemoveParent){
                        if (groupPositionAtAll != -1) {
                            IExpandable groupEntity = (IExpandable) getData().get(groupPositionAtAll);
                            if (!hasSubItems(groupEntity)) {
                                super.remove(groupPositionAtAll);
                                return;
                            }
                        }
                    }
                    int currentGroupPositionInChild = getData().indexOf(currentGroupInChild);
                    notifyItemRangeChanged(currentGroupPositionInChild, getData().size() - currentGroupPositionInChild);
                }else{
                    if (nullRemoveParent && !hasSubItems((IExpandable)currentGroupInChild)) {
                        super.remove(getData().indexOf(currentGroupInChild));
                    }else {
                        notifyItemChanged(getData().indexOf(currentGroupInChild));
                    }
                }
                break;
            case TYPE_GRANDSON:
                T currentGroupInGrandson = null;
                IExpandable currentChildInGrandson = null;
                loop:for(T group : mGroupList){
                    List<IExpandable> childList = ((IExpandable)group).getSubItems();
                    for(IExpandable child : childList){
                        List<MultiItemEntity> grandsonList = child.getSubItems();
                        if(grandsonList.contains(entity)){
                            grandsonIterator.remove();
                            currentGroupInGrandson = group;
                            if(nullRemoveParent && grandsonList.size() == 0){
                                childIterator.remove();
                                currentChildInGrandson = child;
                                if(childList.size() == 0){
                                    groupIterator.remove();
                                }
                            }
                            break loop;
                        }
                    }
                }
                if(getData().contains(entity)) {
                    int index = getData().indexOf(entity);
                    int childPositionAtAll = getParentPositionInAll(index);
                    super.remove(index);
                    if(nullRemoveParent){
                        if (childPositionAtAll != -1) {
                            IExpandable childEntity = (IExpandable) getData().get(childPositionAtAll);
                            if (!hasSubItems(childEntity)) {
                                int groupPositionAtAll = getParentPositionInAll(childPositionAtAll);
                                super.remove(childPositionAtAll);
                                if (groupPositionAtAll != -1) {
                                    IExpandable groupEntity = (IExpandable) getData().get(groupPositionAtAll);
                                    if (!hasSubItems(groupEntity)) {
                                        super.remove(groupPositionAtAll);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    int currentGroupPositionInGrandson = getData().indexOf(currentGroupInGrandson);
                    notifyItemRangeChanged(currentGroupPositionInGrandson, getData().size() - currentGroupPositionInGrandson);
                }else{
                    if (nullRemoveParent){
                        if (getData().contains(currentChildInGrandson) && !hasSubItems(currentChildInGrandson)) {
                            super.remove(getData().indexOf(currentChildInGrandson));
                            int currentGroupPosition = getData().indexOf(currentGroupInGrandson);
                            notifyItemRangeChanged(currentGroupPosition, getData().size() - currentGroupPosition);
                        }
                        if (!hasSubItems((IExpandable)currentGroupInGrandson)) {
                            super.remove(getData().indexOf(currentGroupInGrandson));
                        }
                    } else {
                        if (getData().contains(currentChildInGrandson)){
                            notifyItemChanged(getData().indexOf(currentChildInGrandson));
                        }
                        notifyItemChanged(getData().indexOf(currentGroupInGrandson));
                    }
                }
                break;
        }
    }

    @Override
    protected void convert(@NonNull K holder, T item) {
        switch (holder.getItemViewType()) {
            case TYPE_GROUP:
                convertGroup(holder, (T)item, ((IExpandable)item).isExpanded(), holder.getAdapterPosition());
                break;
            case TYPE_CHILD:
                int childPosition1 = holder.getAdapterPosition();
                int groupPosition1 = getParentPositionInAll(childPosition1);
                convertChild(holder, item, groupPosition1, childPosition1, isLastChild(groupPosition1, item));
                break;
            case TYPE_GRANDSON:
                int gransonPosition2 = holder.getAdapterPosition();
                int childPosition2 = getParentPositionInAll(gransonPosition2);
                int groupPosition2 = getParentPositionInAll(childPosition2);
                convertGrandson(holder, item, groupPosition2, childPosition2, gransonPosition2, isLastGrandson(childPosition2, item));
                break;
        }
    }

    @Override
    protected void convertPayloads(@NonNull K holder, T item, @NonNull List<Object> payloads) {
        if(payloads.isEmpty()) {
            convert(holder, item);
        }else{
            switch (holder.getItemViewType()) {
                case TYPE_GROUP:
                    convertPayloadsGroup(holder, (T)item, ((IExpandable)item).isExpanded(), holder.getAdapterPosition());
                    break;
                case TYPE_CHILD:
                    int childPosition1 = holder.getAdapterPosition();
                    int groupPosition1 = getParentPositionInAll(childPosition1);
                    convertPayloadsChild(holder, item, groupPosition1, childPosition1, isLastChild(groupPosition1, item));
                    break;
                case TYPE_GRANDSON:
                    int gransonPosition2 = holder.getAdapterPosition();
                    int childPosition2 = getParentPositionInAll(gransonPosition2);
                    int groupPosition2 = getParentPositionInAll(childPosition2);
                    convertPayloadsGrandson(holder, item, groupPosition2, childPosition2, gransonPosition2, isLastGrandson(childPosition2, item));
                    break;
            }
        }
    }

    public void convertPayloadsGroup(K holder, T group, boolean isExpanded, int groupPosition) {

    }

    public void convertPayloadsChild(K holder, MultiItemEntity childEntity, int groupPosition, int childPosition, boolean isLastChild) {

    }

    public void convertPayloadsGrandson(K holder, MultiItemEntity grandsonEntity, int groupPosition, int childPosition, int grandsonPosition, boolean isLastGrandson) {

    }


    public T getGroupData(int adapterPosition){
        return (T)getData().get(adapterPosition);
    }

    public T getGroupDataInChild(int childPosition){
        return getData().get(getParentPositionInAll(childPosition));
    }

    public T getGroupDataInGrandson(int adapterPosition){
        return getData().get(getParentPositionInAll(getParentPositionInAll(adapterPosition)));
    }

    public MultiItemEntity getChildDataInGrandson(int adapterPosition){
        return getData().get(getParentPositionInAll(adapterPosition));
    }

    public MultiItemEntity getChildData(int adapterPosition){
        return getData().get(adapterPosition);
    }

    public MultiItemEntity getGransondData(int adapterPosition){
        return getData().get(adapterPosition);
    }

    public void convertGroup(K holder, T group, boolean isExpanded, int groupPosition) {
        reigsterGroupItemClickListener(holder.itemView, holder, group, groupPosition);
    }

    public void reigsterGroupItemClickListener(View itemView, final K holder, final T group, int groupPosition){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(groupExpandable) {
                    int pos = holder.getAdapterPosition();
                    if (((IExpandable) group).isExpanded()) {
                        collapse(pos, false);
                    } else {
                        expand(pos, false);
                    }
                }else{
                    if(getOnItemClickListener() != null) {
                        setOnItemClick(v, holder.getAdapterPosition());
                    }
                }
            }
        });
    }

    public void convertChild(K holder, MultiItemEntity childEntity, int groupPosition, int childPosition, boolean isLastChild) {
        reigsterChildItemClickListener(holder.itemView, holder, childEntity, groupPosition, childPosition);
    }

    public void reigsterChildItemClickListener(View itemView, final K holder, final MultiItemEntity childEntity, int groupPosition, int childPosition){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

    public void convertGrandson(K holder, MultiItemEntity grandsonEntity, int groupPosition, int childPosition, int grandsonPosition, boolean isLastGrandson){

    }


    public boolean isFristChild(int groupPosition, MultiItemEntity child){
        if(child == null){
            return false;
        }else {
            List<T> data = getData();
            List<MultiItemEntity> childList = ((IExpandable) data.get(groupPosition)).getSubItems();
            int index = childList.indexOf(child);
            return index == 0;
        }
    }

    public boolean isFristGrandson(int childPosition, MultiItemEntity grandson){
        if(grandson == null){
            return false;
        }else {
            List<T> data = getData();
            List<MultiItemEntity> childList = ((IExpandable) data.get(childPosition)).getSubItems();
            int index = childList.indexOf(grandson);
            return index == 0;
        }
    }

    public boolean isLastGrandson(int childPosition, MultiItemEntity grandson){
        if(grandson == null){
            return false;
        }else {
            List<T> data = getData();
            List<MultiItemEntity> childList = ((IExpandable) data.get(childPosition)).getSubItems();
            int index = childList.indexOf(grandson);
            return index == childList.size() - 1;
        }
    }

    /**
     * 获取子项中最后一个孙项的索引
     * 失败返回0
     * @param child
     * @return
     */
    public int getLastGrandsonPositionInChild(MultiItemEntity child){
        if(child != null && child instanceof IExpandable){
            List<T> data = getData();
            List<MultiItemEntity> childList = ((IExpandable) child).getSubItems();
            if(childList == null || childList.size() == 0){
                return 0;
            }else{
                return data.indexOf(childList.get(childList.size() - 1));
            }
        }else{
            return 0;
        }

    }

    public boolean isOnlyOneChildInGroup(int childPosition){
        int groupPosition = getParentPositionInAll(childPosition);
        if(groupPosition == -1){
            return false;
        }
        IExpandable group = (IExpandable)getData().get(groupPosition);
        return group.getSubItems().size() == 1;
    }

    public boolean isLastChild(int groupPosition, MultiItemEntity child){
        if(child == null){
            return false;
        }else {
            List<T> data = getData();
            List<MultiItemEntity> childList = ((IExpandable) data.get(groupPosition)).getSubItems();
            int index = childList.indexOf(child);
            return index == childList.size() - 1;
        }
    }

    public boolean isLastSecondChild(int groupPosition, MultiItemEntity child){
        if(child == null){
            return false;
        }else {
            List<T> data = getData();
            List<MultiItemEntity> childList = ((IExpandable) data.get(groupPosition)).getSubItems();
            int index = childList.indexOf(child);
            return index == childList.size() - 2;
        }
    }

    @Override
    public void replaceData(@NonNull Collection<? extends T> data) {
        // 不是同一个引用才清空列表
        if (data != mGroupList) {
            mGroupList.clear();
            mGroupList.addAll(data);
        }
        super.replaceData(data);
        setOldExpandCollapseStatus();
    }

    /**
     * 设置列表之前关闭或者展开的状态
     */
    private void setOldExpandCollapseStatus(){
        if(!expandCollapseHashMap.isEmpty()) {
            Set<Object> set = expandCollapseHashMap.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()){
                Object obj = iterator.next();
                if(obj != null) {
                    List<T> dataList = getData();
                    for (T t : dataList) {
                        if (t != null && t instanceof TagAbstractExpandableItem) {
                            if (obj.equals(((TagAbstractExpandableItem) t).getTag())) {
                                boolean isExpand = expandCollapseHashMap.get(((TagAbstractExpandableItem) t).getTag());
                                if (isExpand) {
                                    expand(getData().indexOf(t));
                                } else {
                                    collapse(getData().indexOf(t));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public List<T> getGroupList(){
        return mGroupList;
    }

    /**
     * 组项是否可以展开关闭
     * @return
     */
    public boolean isGroupExpandable() {
        return groupExpandable;
    }

    /**
     * 设置组项展开关闭可用性
     * @param groupExpandable
     */
    public void setGroupExpandable(boolean groupExpandable) {
        this.groupExpandable = groupExpandable;
    }

    /**
     * 子项是否可以展开关闭
     * @return
     */
    public boolean isChildExpandable() {
        return childExpandable;
    }

    /**
     * 设置子项展开关闭可用性
     * @param childExpandable
     */
    public void setChildExpandable(boolean childExpandable) {
        this.childExpandable = childExpandable;
    }

    public boolean isOnlyExpandOne() {
        return isOnlyExpandOne;
    }

    public void setOnlyExpandOne(boolean onlyExpandOne) {
        isOnlyExpandOne = onlyExpandOne;
    }

    @Override
    public int collapse(int position) {
        recordExpandCollapse(position, false);
        return super.collapse(position);
    }

    @Override
    public int collapse(int position, boolean animate) {
        recordExpandCollapse(position, false);
        return super.collapse(position, animate);
    }

    @Override
    public int collapse(int position, boolean animate, boolean notify) {
        recordExpandCollapse(position, false);
        return super.collapse(position, animate, notify);
    }

    @Override
    public int expand(int position) {
        recordExpandCollapse(position, true);
        return super.expand(position);
    }

    @Override
    public int expand(int position, boolean animate) {
        recordExpandCollapse(position, true);
        return super.expand(position, animate);
    }

    @Override
    public int expand(int position, boolean animate, boolean shouldNotify) {
        recordExpandCollapse(position, true);
        return super.expand(position, animate, shouldNotify);
    }

    private void recordExpandCollapse(int position, boolean isExpand){
        try {
            T t = getData().get(position);
            if(t instanceof TagAbstractExpandableItem){
                expandCollapseHashMap.put(((TagAbstractExpandableItem)t).getTag(), isExpand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
