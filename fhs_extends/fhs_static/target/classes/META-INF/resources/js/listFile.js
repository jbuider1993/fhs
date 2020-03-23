function createFileList(tempDivId,fileJsons)
{
	    var html = ''
	    html = html + '<table cellspacing="0" cellpadding="0">';
	    var item = '';
	    for (var i = 0;i < fileJsons.length;i++)
	    {
	    	item = fileJsons[i];
	    	html = html + "<tr>";			
			
		    var regImgPtn = /\.jpg$|\.jpeg$|\.gif$|\.png$/ig;
			var isImgType = regImgPtn.test(item.fileSaveName);
			
			var showFileName = String(item.fileName);
			var max_length_file_name = 10;
			var suffixName = "...";
			var fileType = showFileName.substr(showFileName.length - 3,3);
			var fileClassName;
			if (showFileName.length > 20)
			{
				showFileName = showFileName.substr(0,max_length_file_name) + suffixName;
			}
			
			if (isImgType)
			{
				fileClassName = 'file-img';
			}
			else
			{
				
				switch(fileType)
				{
					case 'txt':{fileClassName = 'file-txt';}break;
					case 'rar':{fileClassName = 'file-rar';}break;				
					case 'doc':;				
					case 'docx':{fileClassName = 'file-word';}break;		
					case 'xlsx':{fileClassName = 'file-excel';}break;	
						
					default:
					{
						//其他类型
					}
					
				}
				
			}
			html = html + "<td style=\"border:0;\">";
			html = html + "<a  class='" + fileClassName + "' href='javascript:void(0)'></a>";
			html = html + "</td>";				
			html = html + "<td style=\"border:0;\">";
			
			if (!isImgType)
			{
				html = html + "<a  fileSaveName='" + item.fileSaveName + "' fileName='" + item.fileName + "' onclick=\'downFile(this.fileSaveName,this.fileName)' href='javascript:void(0)'>" + showFileName + "</a>";
			}
			else 
			{				
				html = html + "<a   fileSaveName='" + item.fileSaveName + "' fileName='" + item.fileName + "' class='showImg' href='javascript:void(0)' onclick='top.popupImg(showFileUrl(this.fileSaveName,this.fileName))' >" + showFileName + "</a>";
			}
			
			html = html + "</td>";
			html = html + "</tr>";
	    }
	    html = html + "</table>";
		$('#' + tempDivId).html(html);
   }
   
   function downFile(saveName,showName)
   {
	   location.href = showFileUrl(saveName,showName);
   }
   
   function showFileUrl(saveName,showName)
   {
	   var param =  '?fileName=' + saveName + '&showFileName=' + showName;
	   return top.$("#base_path").val() + 'pub/pub_downloadFile.action' + param;
   }