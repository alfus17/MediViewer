// 초기화 공통 로직
function resetHistData() {
	histItems.splice(0, histItems.length);
}

// 환자 데이터 표시
function setHistPData(pID, pName) {
	$("#histPID").text(pID);
	$("#histPName").text(pName);
	
	getHistData(pID, pName);
}