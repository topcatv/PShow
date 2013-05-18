package org.pshow.service;

import org.pshow.repo.datamodel.content.ContentRef;

/**
 * 工作流服务
 * @author edilyxin
 *
 */
public interface WorkFlowService {
	
	/**
	 *  根据一个内容发起一个流程
	 * @param contentRef
	 * @return
	 */
	public boolean startFlowWithContent(ContentRef contentRef);

}
