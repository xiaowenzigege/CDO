jQuery.extend({
	 		getDays:function(_startDate,_endDate,_splitChar){
	 			var splitChar="-";
	 			if(typeof(_splitChar) != "undefined" &&
	 					_splitChar!=null && _splitChar!=""){
	 				splitChar=_splitChar;
	 			}
                //把时间按分割符切成数组
                var start=_startDate.split(splitChar);
                var end=_endDate.split(splitChar);
                //转为毫秒数
                var btime=new Date(start[0],start[1]-1,start[2]);
                var etime=new Date(end[0],end[1]-1,end[2]);
                //计算相差天数
                time=(etime.getTime()-btime.getTime())/(1000*24*60*60);
                return time;
            },
            
            checkDays:function(_startId,_endId,_days,_splitChar){
                var sdate=$("#"+_startId).val();
                var edate=$("#"+_endId).val();
                var errorMsg=""; 
			    if(sdate.length <= 0||edate.length <= 0){
			    	errorMsg="请择时间范围";
					return errorMsg;
			   }
			   var days=$.getDays(sdate,edate,_splitChar); 
               if(days<0){
            	   errorMsg="结束时间必须大于等于开始时间";
                   return errorMsg;
                }
               if(typeof(_days) == "undefined"||_days==null){
            	   return errorMsg;
               }
			   if(days>_days){
				   errorMsg="时间范围必须在30天之内";
				   return errorMsg;
				}
			   return errorMsg;
            }
            
      });   