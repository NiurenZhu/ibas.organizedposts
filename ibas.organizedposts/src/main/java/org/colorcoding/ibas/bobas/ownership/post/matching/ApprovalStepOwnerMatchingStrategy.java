package org.colorcoding.ibas.bobas.ownership.post.matching;

import org.colorcoding.ibas.bobas.approval.ApprovalFactory;
import org.colorcoding.ibas.bobas.approval.IApprovalData;
import org.colorcoding.ibas.bobas.approval.IApprovalProcess;
import org.colorcoding.ibas.bobas.approval.IApprovalProcessManager;
import org.colorcoding.ibas.bobas.approval.IApprovalProcessStep;
import org.colorcoding.ibas.bobas.core.IBORepository;
import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.ownership.IDataOwnership;
import org.colorcoding.ibas.bobas.repository.InvalidTokenException;
import org.colorcoding.ibas.organizedposts.repository.BORepositoryOrganizedPosts;

public class ApprovalStepOwnerMatchingStrategy extends BasisMatchingStrategy {
	/**
	 * 判断当前BO是否适用此规则
	 *
	 * @param bo
	 * @param user
	 * @return
	 */
	@Override
	public boolean match(IDataOwnership bo, IUser user) {
		if (!(bo instanceof IApprovalData)) {
			// 业务对象不是需要审批的数据，退出处理
			return false;
		}
		IApprovalProcessManager apManager = ApprovalFactory.create().createManager();
		IApprovalProcess approvalProcess = apManager.checkProcess((IApprovalData) bo, this.createRepository());
		if (approvalProcess != null) {
			for (IApprovalProcessStep approvalProcessStep : approvalProcess.getProcessSteps()) {
				if (approvalProcessStep.getOwner().getId() == user.getId()) {
					return true;
				}
			}
		}
		return false;
	}

	private BORepositoryOrganizedPosts repository;

	private IBORepository createRepository() {
		if (repository == null) {
			try {
				repository = new BORepositoryOrganizedPosts();
				repository.setUserToken(OrganizationFactory.SYSTEM_USER.getToken());
			} catch (InvalidTokenException e) {
				throw new RuntimeException(e);
			}
		}
		return repository.getRepository();
	}
}
