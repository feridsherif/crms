package com.hilcoe.crms.dto;

import java.math.BigDecimal;
import java.util.List;

public class InventoryReportDTO {
	public static class ItemReport {
		public Long id;
		public String name;
		public String unit;
		public BigDecimal quantity;
		public BigDecimal threshold;
		public SupplierInfo supplier;
		public List<StockMovementInfo> recentMovements;
		public BigDecimal itemValue; // Optional: if price/cost is available
	}

	public static class SupplierInfo {
		public Long id;
		public String name;
		public String contact;
		public String phone;
		public String terms;
	}

	public static class StockMovementInfo {
		public String date;
		public BigDecimal quantityChange;
		public String reason;
	}

	public List<ItemReport> items;
	public List<ItemReport> lowStockItems;
	public BigDecimal totalInventoryValue;
}
