package org.colorcoding.ibas.organizedposts.bo.post;

import org.colorcoding.ibas.bobas.bo.BusinessObjects;
import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.ICondition;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.organizedposts.bo.post.IPost;

import java.beans.PropertyChangeEvent;

/**
 * Created by czc on 2017/10/25.
 */
public class Posts extends BusinessObjects<IPost, IPost> implements IPosts {
	/**
	 * 业务对象名称
	 */
	public static final String BUSINESS_OBJECT_NAME = "Posts";

	/**
	 * 序列化版本标记
	 */
	private static final long serialVersionUID = 7277502956302822182L;

	/**
	 * 构造方法
	 */
	public Posts() {
		super();
	}

	/**
	 * 构造方法
	 *
	 * @param parent 父项对象
	 */
	public Posts(IPost parent) {
		super(parent);
	}

	/**
	 * 元素类型
	 */
	@Override
	public Class<?> getElementType() {
		return Post.class;
	}

	/**
	 * 创建数据权限项
	 *
	 * @return 数据权限项
	 */
	@Override
	public IPost create() {
		IPost item = new Post();
		if (this.add(item)) {
			return item;
		}
		return null;
	}

	@Override
	protected void afterAddItem(IPost item) {
		super.afterAddItem(item);
		// TODO 设置关联值
		if (this.getParent() != null) {
			if (item.getBelonging().equals(0)) {
				item.setBelonging(this.getParent().getObjectKey());
			}
			if (item.getValidDate() == null) {
				item.setValidDate(this.getParent().getValidDate());
			}
			if (item.getInvalidDate() == null) {
				item.setInvalidDate(this.getParent().getInvalidDate());
			}
		}
	}

	@Override
	public ICriteria getElementCriteria() {
		ICriteria criteria = new Criteria();
		ICondition condition = criteria.getConditions().create();
		condition.setAlias("Belonging");
		condition.setValue(this.getParent().getObjectKey());
		// TODO 添加关联查询条件
		return criteria;
	}

	@Override
	public void onParentPropertyChanged(PropertyChangeEvent evt) {
		// 此处不能调用父类方法,更新操作时会改变子对象的ObjectKey属性
		// super.onParentPropertyChanged(evt);
		// TODO 设置关联值
		if (evt.getPropertyName().equals(Post.PROPERTY_OBJECTKEY.getName())) {
			for (IPost item : this) {
				item.setBelonging(this.getParent().getObjectKey());
			}
		}
		if (evt.getPropertyName().equals(Post.PROPERTY_VALIDDATE.getName())) {
			for (IPost item : this) {
				item.setValidDate(this.getParent().getValidDate());
			}
		}
		if (evt.getPropertyName().equals(Post.PROPERTY_INVALIDDATE.getName())) {
			for (IPost item : this) {
				item.setInvalidDate(this.getParent().getInvalidDate());
			}
		}
	}
}
