package com.yx.scan.service;

import com.yx.scan.model.SystemApiScanSaveDTO;

/**
 * This is a Description
 *
 * @author yx
 * @date 2019/12/17
 */

public interface SystemApiScanService {
    /**
     * 批量保存
     *
     * @param data
     * @return
     */
    Boolean batchSave(SystemApiScanSaveDTO data);
}
