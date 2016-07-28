$(document).ready(function() {

	var lang = $("#lang").val();

	var dropDrapText = (lang == "vi") ?  "Kéo & thả file" : "Drag&Drop files here";
	var uploadBtn = (lang == "vi") ?  "Tải File" : "Browse File";
	var label_or = (lang == "vi") ?  "hoặc" : "or";

    //Example 1
    $('#filer_input').filer({
		showThumbs: true
    });

    //Example 2
    var filerOption = {
            limit: 1,
            maxSize: 100,
            extensions: ['jpg', 'jpeg', 'png', 'gif', "tiff"],
            changeInput: '<div class="jFiler-input-dragDrop"><div class="jFiler-input-inner"><div class="jFiler-input-icon"><i class="icon-jfi-cloud-up-o"></i></div><div class="jFiler-input-text"><h3>' + dropDrapText + '</h3> <span style="display:inline-block; margin: 15px 0">' + label_or + '</span></div><a class="jFiler-input-choose-btn blue">' + uploadBtn + '</a></div></div>',
            showThumbs: true,
            theme: "dragdropbox",
            templates: {
                box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
                item: '<li class="jFiler-item">\
                            <div class="jFiler-item-container">\
                                <div class="jFiler-item-inner">\
                                    <div class="jFiler-item-thumb">\
                                        <div class="jFiler-item-status"></div>\
                                        <div class="jFiler-item-info">\
                                            <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                            <span class="jFiler-item-others">{{fi-size2}}</span>\
                                        </div>\
                                        {{fi-image}}\
                                    </div>\
                                    <div class="jFiler-item-assets jFiler-row">\
                                        <ul class="list-inline pull-left">\
                                            <li>{{fi-progressBar}}</li>\
                                        </ul>\
                                        <ul class="list-inline pull-right">\
                                            <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                        </ul>\
                                    </div>\
                                </div>\
                            </div>\
                        </li>',
                itemAppend: '<li class="jFiler-item">\
                                <div class="jFiler-item-container">\
                                    <div class="jFiler-item-inner">\
                                        <div class="jFiler-item-thumb">\
                                            <div class="jFiler-item-status"></div>\
                                            <div class="jFiler-item-info">\
                                                <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                                <span class="jFiler-item-others">{{fi-size2}}</span>\
                                            </div>\
                                            {{fi-image}}\
                                        </div>\
                                        <div class="jFiler-item-assets jFiler-row">\
                                            <ul class="list-inline pull-left">\
                                                <li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
                                            </ul>\
                                            <ul class="list-inline pull-right">\
                                                <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                            </ul>\
                                        </div>\
                                    </div>\
                                </div>\
                            </li>',
                progressBar: '<div class="bar"></div>',
                itemAppendToEnd: false,
                removeConfirmation: true,
                _selectors: {
                    list: '.jFiler-items-list',
                    item: '.jFiler-item',
                    progressBar: '.bar',
                    remove: '.jFiler-item-trash-action'
                }
            },
            dragDrop: {
                dragEnter: Object,
                dragLeave: Object,
                drop: Object,
            },
            addMore: false,
            clipBoardPaste: true,
            captions: {
                button: "Choose Files",
                feedback: "Choose files To Upload",
                feedback2: "files were chosen",
                drop: "Drop file here to Upload",
                removeConfirmation: "Are you sure you want to remove this file?",
                errors: {
                    filesLimit: "Only {{fi-limit}} files are allowed to be uploaded.",
                    filesType: "Only Images are allowed to be uploaded.",
                    filesSize: "{{fi-name}} is too large! Please upload file up to {{fi-maxSize}} MB.",
                    filesSizeAll: "Files you've choosed are too large! Please upload files up to {{fi-maxSize}} MB."
                }
            }
        };
    var singleFileUploader = $("#filer_input2, #filer-single").filer(filerOption);
    var batchFileUploader = $('#filer-batch').filer(function(obj){
    	filerOption.limit = 100;
    	filerOption.addMore = true;
    	var files = [];
    	filerOption.onSelect = function(file){
    		var fileReader = new FileReader();
    		fileReader.onload = function(e) {
    			 var imageInfo = {};
				 imageInfo.fileToBase64 = e.target.result.split(",")[1];
				 imageInfo.originalImageName = file.name;
				 files.push(imageInfo);
				 $('#fileBase64Code').data('uploadingfiles', files);
			 };       
			fileReader.readAsDataURL(file);
    	}
    	filerOption.onRemove = function(file){
    		var fileReader = new FileReader();
    		fileReader.onload = function(e) {
    			 var imageInfo = {};
				 imageInfo.fileToBase64 = e.target.result;
				 imageInfo.originalImageName = file.name;
				 files.pop(imageInfo);
				 $('#fileBase64Code').data('uploadingfiles', files);
			 };       
			fileReader.readAsDataURL(file);
    	}
    	return filerOption;
    })
    
    $('#batch-upload').on('change', function(e){
    	var filerKit = $("#filer_input2").prop("jFiler");
    	if($(this).is(':checked')){
    		singleFileUploader.closest('.file-container').hide();
    		batchFileUploader.closest('.file-container').show();
    	}else{
    		singleFileUploader.closest('.file-container').show();
    		batchFileUploader.closest('.file-container').hide();
    	}
    })

});
