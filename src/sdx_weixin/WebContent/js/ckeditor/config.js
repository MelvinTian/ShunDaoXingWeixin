/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'zh-cn';
	config.uiColor='#A1CFF3';
	config.height = 100;
	config.font_names =
	    'Arial/Arial, Helvetica, sans-serif;' +
	    'Times New Roman/Times New Roman, Times, serif;' +
	    'Verdana;'+
	    '宋体;'+
	    '微软雅黑;'+
	    '黑体;';
	config.toolbar =
		[
		    ['Undo','Redo','RemoveFormat'],
		    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		    ['Format','Font','FontSize','TextColor','BGColor']
		];
};
