MeditorConfig = function( config ) {
	//组件，key是组件在页面中的名称，value是组件对象
	config.plugins =
		[{'button_type':'ButtonEditor'},{'date_type':'DateEditor'},{'divider_type':'DividerEditor'},{'footer_type':'FooterEditor'},
		 {'header_type':'HeaderEditor'},{'input_type':'InputEditor'},{'hidden_type':'InputHiddenEditor'},
		 {'label_type':'LabelEditor'},{'link_type':'LinkEditor'},{'list_type':'ListEditor'},{'listrow_type':'ListRowEditor'},{'listview_type':'ListViewEditor'},
		 {'no_type':'NoButtonEditor'},{'onelinetwocolumn_type':'OneLineTwoColumnEditor'},
		 {'show_label_type':'ShowLabelEditor'},{'submitbtn_type':'SubmitButtonEditor'},{'tablelabel_type':'TableLabelEditor'},
		 {'textarea_type':'TextareaEditor'},{'twobutton_type':'TwoButtonEditor'},{'yes_type':'YesButtonEditor'}
		 ,{'radio_type':'RadioEditor'},{'select_type':'SelectEditor'},{'headernav_type':'HeaderNavEditor'},{'linktitle_type':'LinkTitleEditor'}];
	//设计器中的组件显示样式
	config.specialclass =
		[{'button_type':['padding_left_right']},{'date_type':['padding_left_right']},{'divider_type':['bd_height']},
		 {'footer_type':['footer_li_btn']},{'tablelabel_type':['']},{'headernav_type':['']},
		 {'header_type':[]},{'input_type':['padding_left_right','input_text']},
		 {'hidden_type':['padding_left_right','hidden_bg']},
		 {'label_type':['padding_left_right']},{'link_type':['padding_left_right']},
		 {'list_type':['padding_left_right']}, {'listrow_type':['padding_left_right']},{'listview_type':['padding_left_right']},
		 {'no_type':['padding_left_right']},{'onelinetwocolumn_type':['padding_left_right','text-label-bd-bottom']},
		 {'show_label_type':['padding_left_right','placeLabel','label_lineheight']},{'submitbtn_type':['padding_left_right']},
		 {'textarea_type':['padding_left_right']},{'twobutton_type':['padding_left_right']},{'yes_type':['padding_left_right']}
		 ,{'radio_type':['padding_left_right']},{'select_type':['padding_left_right']},{'linktitle_type':['padding_left_right']}];
	config.fillckeditor =
		['label_type','show_label_type','list_type','onelinetwocolumn_type','listrow_type','select_type','tablelabel_type'];
	return config;
};
