package org.colorcoding.ibas.organizedposts.bo.ownership;

import java.beans.PropertyChangeEvent;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.ibas.bobas.bo.BusinessObjects;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.organizedposts.MyConfiguration;

/**
 * 数据权限项 集合
 */
@XmlType(name = OwnershipItems.BUSINESS_OBJECT_NAME, namespace = MyConfiguration.NAMESPACE_BO)
@XmlSeeAlso({ OwnershipItem.class })
public class OwnershipItems extends BusinessObjects<IOwnershipItem, IOwnership> implements IOwnershipItems {

	/**
	 * 业务对象名称
	 */
	public static final String BUSINESS_OBJECT_NAME = "OwnershipItems";

	/**
	 * 序列化版本标记
	 */
	private static final long serialVersionUID = -3533985481804390214L;

	/**
	 * 构造方法
	 */
	public OwnershipItems() {
		super();
	}

	/**
	 * 构造方法
	 * 
	 * @param parent
	 *            父项对象
	 */
	public OwnershipItems(IOwnership parent) {
		super(parent);
	}

	/**
	 * 元素类型
	 */
	public Class<?> getElementType() {
		return OwnershipItem.class;
	}

	/**
	 * 创建数据权限项
	 * 
	 * @return 数据权限项
	 */
	public IOwnershipItem create() {
		IOwnershipItem item = new OwnershipItem();
		if (this.add(item)) {
			return item;
		}
		return null;
	}

	@Override
	protected void afterAddItem(IOwnershipItem item) {
		super.afterAddItem(item);
	}

	@Override
	public ICriteria getElementCriteria() {
		ICriteria criteria = super.getElementCriteria();
		return criteria;
	}

	@Override
	public void onParentPropertyChanged(PropertyChangeEvent evt) {
		super.onParentPropertyChanged(evt);
	}
}
