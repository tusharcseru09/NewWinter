$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/products",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "product_id"},
			      { "mData": "productCode" },
				  { "mData": "productName" },
				  { "mData": "productDesc" },
				  { "mData": "unitKey" },
				  { "mData": "categoryCode" },
				  { "mData": "purchase_price" },
				  { "mData": "sales_price" }
			]
	 })
});