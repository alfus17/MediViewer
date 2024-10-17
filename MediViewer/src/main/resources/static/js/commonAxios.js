const getDefList = () => {
	axios.get("/api/lists")
		.then(response => {
			nowPage = null;
			const rData = response.data;
			setCount(rData.count);
			setItems(items, rData.items);
			
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
			
			const listContainer = document.querySelector("#liForEachSpan");
			items.forEach(item => {
				const li = document.createElement("li");
				li.innerHTML = `
					<input class="checkbox" type="checkbox" name="selectOne">
			        <span class="w10">${item.pid}</span>
			        <span class="w10">${item.pname}</span>
			        <span class="w8">${item.modality}</span>
			        <span class="span_study_desc">${item.studyDesc}</span>
			        <span class="w10">${item.studyDate}</span>
			        <span class="w5">${item.reportStatus}</span>
			        <span class="w5">${item.seriesCnt}</span>
			        <span class="w5">${item.imageCnt}</span>
				`;
				listContainer.appendChild(li);
			})
		})
		.catch(error => console.error(error));
}

const getHistoryList = (params) => {
	axios.post("/api/lists/histories", params)
		.then(response => {
			const rData = response.data;
			resetHistItems();
			setItems(histItems, rData.items);
			
			$("#histPID").text(rData.pID);
			$("#histPName").text(rData.pName);
			const listContainer = document.querySelector("#histLiForEachSpan");
				items.forEach(item => {
					const li = document.createElement("li");
					li.innerHTML = `
				        <span class="w8">${item.modality}</span>
				        <span class="span_study_desc">${item.studyDesc}</span>
				        <span class="w10">${item.studyDate}</span>
				        <span class="w5">${item.reportStatus}</span>
				        <span class="w5">${item.seriesCnt}</span>
				        <span class="w5">${item.imageCnt}</span>
					`;
					listContainer.appendChild(li);
				})
		})
		.catch(error => console.error(error));
}

// axios 공통 로직
function setParams() {
	const pID = $("#pid").val();
	const pName = $("#pname").val();
	const modality = $("#modality").val();
	const state = $("#state").val();
	const slice = $("#slice").val();
	const nowPage = 1;
	const date = [];
	
	const param = {
		pID,
		pName,
		modality,
		state,
		slice,
		nowPage,
		sDate: date[0],
		eDate: date[1]
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
	
	return null;
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

function resetContentItems() {
	items.splice(0, items.length);
	$("#liForEachSpan").empty();
}

function resetHistItems () {
	histItems.splice(0, histItems.length);
	$("#histLiForEachSpan").empty();
}