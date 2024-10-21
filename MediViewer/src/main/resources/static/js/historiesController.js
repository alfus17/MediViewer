// 환자 데이터 표시
function setHistPData(pID, pName) {
	$("#histPID").text(pID);
	$("#histPName").text(pName);
	
	getHistoryList(pID, pName);
}

// 초기화 공통 로직
function resetHistData() {
	resetItems(histItems);
}

