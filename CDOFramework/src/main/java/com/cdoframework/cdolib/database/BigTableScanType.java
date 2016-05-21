package com.cdoframework.cdolib.database;

import java.io.Serializable;

/**
 * 
 * Type为特定全库全表扫描任务类型
 * 
 */
public interface BigTableScanType extends Serializable
{
	int BIGTABLE_SEQUENCE_SCAN_NOUPDATE = 1;
	int BIGTABLE_SEQUENCE_SCAN_UPDATE = 2;
	int BIGTABLE_BALANCE_SCAN_UPDATE = 3;
}
