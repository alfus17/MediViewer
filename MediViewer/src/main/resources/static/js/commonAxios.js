const getDefList = () => {
	axios.get("/api/lists")
		.then(response => {
			nowPage = null;
			const rData = response.data;
			setCount(rData.count);
			setItems(items, rData.items);
			setModalities(rData.modalities);
			setStatus(rData.reportStatus);
			
			$("#histPID").text('');
			$("#histPName").text('');
			const listContainer = document.querySelector("#liForEachSpan");
			items.forEach(item => {
				const li = document.createElement("li");
				li.setAttribute("name", "listItem");
				li.setAttribute("value", item.studyKey);
				li.innerHTML = `
					<input class="checkbox" type="checkbox" name="selectOne">
			        <span name="pID" class="w10">${item.pid}</span>
			        <span name="pName" class="w10">${item.pname}</span>
			        <span name="modality" class="w8">${item.modality}</span>
			        <span name="studyDesc" class="span_study_desc">${item.studyDesc}</span>
			        <span name="studyDate" class="w10">${item.studyDate}</span>
			        <span name="reportStatus" class="w5">${item.reportStatus}</span>
			        <span name="seriesCnt" class="w5">${item.seriesCnt}</span>
			        <span name="imageCnt" class="w5">${item.imageCnt}</span>
				`;
				listContainer.appendChild(li);
			})
		})
		.catch(error => console.error(error));
}

const getQueryList = (params) => {
	axios.post("/api/lists/query", params)
		.then(response => {
			nowPage = null;
			const rData = response.data;
			resetContentItems();
			resetHistItems();
			setCount(rData.count);
			setItems(items, rData.items);
			
			$("#histPID").text('');
			$("#histPName").text('');
			const listContainer = document.querySelector("#liForEachSpan");
			items.forEach(item => {
				const li = document.createElement("li");
				li.setAttribute("name", "listItem");
				li.setAttribute("value", item.studyKey);
				li.innerHTML = `
					<input class="checkbox" type="checkbox" name="selectOne">
			        <span name="pID" class="w10">${item.pid}</span>
			        <span name="pName" class="w10">${item.pname}</span>
			        <span name="modality" class="w8">${item.modality}</span>
			        <span name="studyDesc" class="span_study_desc">${item.studyDesc}</span>
			        <span name="studyDate" class="w10">${item.studyDate}</span>
			        <span name="reportStatus" class="w5">${item.reportStatus}</span>
			        <span name="seriesCnt" class="w5">${item.seriesCnt}</span>
			        <span name="imageCnt" class="w5">${item.imageCnt}</span>
				`;
				listContainer.appendChild(li);
			})
		})
		.catch(error => console.error(error));
}

const getHistoryList = (params) => {
	axios.post("/api/lists/histories", params)
		.then(response => {
			console.log(response);
			const rData = response.data;
			resetHistItems();
			setItems(histItems, rData.worklist);
			
			$("#histPID").text(rData.pid);
			$("#histPName").text(rData.pname);
			const listContainer = document.querySelector("#histLiForEachSpan");
				histItems.forEach(item => {
					const li = document.createElement("li");
					li.innerHTML = `
				        <span class="w8">${item.modality}</span>
				        <span class="span_study_desc">${item.studyDesc}</span>
				        <span class="w10">${item.studyDate}</span>
				        <span class="w10">${item.reportStatus}</span>
				        <span class="w5">${item.seriesCnt}</span>
				        <span class="w5">${item.imageCnt}</span>
					`;
					listContainer.appendChild(li);
				})
		})
		.catch(error => console.error(error));
}

const getImgPreview = (studyKey, element) => {
	axios.get(`/api/lists/preview/${studyKey}`)
		.then(response => {
			cornerstoneWADOImageLoader.external.cornerstone = cornerstone;
			cornerstoneWADOImageLoader.external.cornerstoneTools = cornerstoneTools;
			
			const rData = response.data;
			
			const dicomPath = rData.path;
			const dicomFile = rData.fname;
			
			const imageId = `wadouri:dcm/${dicomPath.replace(/\\/g, '/')}${dicomFile}`;
			
			cornerstone.loadImage(imageId).then(image => {
	            cornerstone.displayImage(element, image);
	        }).catch(err => {
				console.log('이미지 로드 실패 : ', err);
			});
		})
		.catch(error => console.error(error));
}


const testAxios = () => {
	axios.get('/api/lists/test/16')
		.then(response => {
			let currentIndex = 0;
			
			cornerstoneWADOImageLoader.external.cornerstone = cornerstone;
			cornerstoneWADOImageLoader.external.cornerstoneTools = cornerstoneTools;
			
			const rData = response.data;
			testImgItems.splice(0, testImgItems.length);
			testImgItems.push(...rData.imageFileName);
			
			const e = document.getElementById('testImageArea');
			cornerstone.enable(e);
			
			displayImage(currentIndex);
			
			
			function displayImage(index) {
		        if (index < 0 || index >= testImgItems.length) {
		            return;
		        }
		        cornerstone.loadImage(testImgItems[index]).then(image => {
		            cornerstone.displayImage(e, image);
		        }).catch(err => {
		            console.error("Image load error:", err);
		        });
		    }
		    
		    e.addEventListener('wheel', (event) => {
				event.preventDefault();
				
				if (event.deltaY > 0) { // 휠을 아래로 스크롤
	                if (currentIndex < testImgItems.length - 1) {
	                    currentIndex++;
	                    displayImage(currentIndex);
	                }
	            } else if (event.deltaY < 0) { // 휠을 위로 스크롤
	                if (currentIndex > 0) {
	                    currentIndex--;
	                    displayImage(currentIndex);
	                }
	            }
			})
		});
}






// axios 공통 로직
function setParams() {
	const pid = $("#pid").val();
	const pname = $("#pname").val();
	const modality = $("#modality").val();
	const state = parseInt($("#status").val(), 10);
	const slice = parseInt($("#slice").val(), 10);
	const nowPage = 1;
	const date = [];
	
	const param = {
		pid,
		pname,
		modality,
		state,
		slice,
		nowPage,
		sdate: date[0],
		edate: date[1]
	}
	
	return param;
}

function setDate(days) {
	const today = new Date();
	const year = today.getFullYear();
	const month = today.getMonth() + 1;
	const date = today.getDate();
	let beforeYear = 0;
	let beforeMonth = 0;
	let beforeDate = 0;
	
	if(days !== null) {
		today.setDate(today.getDate() - days);
        const past = new Date(today);
        beforeYear = past.getFullYear();
        beforeMonth = past.getMonth() + 1;
        beforeDate = past.getDate();
		switch (days) {
			case '1' : return [`${year}-${month}-${date}`,`${year}-${month}-${date}`];
			case '3' :
			case '7' :
			case '30' : 
				return [`${beforeYear}-${beforeMonth}-${beforeDate}`,`${year}-${month}-${date}`]
			default : 
		}		
	}
	
	return ['1990-01-01', `${year}-${month}-${date}`];
}

function setCount(count) {
	totalCnt = count;
}

function setItems(dataArray, item) {
	if(nowPage === null){
		nowPage = 1;
	}
	
	if(item != null && item.length > 0){
		dataArray.push(...item)
	}
}

function setModalities(modalities) {
	const modalityList = document.querySelector("#modality");
	
	const defOption = document.createElement("option");
	defOption.setAttribute("value", "");
	defOption.innerText = '장비 선택';
	modalityList.appendChild(defOption);
	
	modalities.forEach(e => {
		const option = document.createElement("option");
		option.setAttribute("value", e);
		option.innerText = e;
		modalityList.appendChild(option);
	})
}

function setStatus(reportStatus) {
	const statusList = $("#status");
	reportStatus.forEach(e => {
		statusList.find(`option[value=${e}]`).prop("disabled", false);
	})
}

function resetContentItems() {
	items.splice(0, items.length);
	$("#liForEachSpan").empty();
}

function resetHistItems () {
	histItems.splice(0, histItems.length);
	$("#histLiForEachSpan").empty();
}