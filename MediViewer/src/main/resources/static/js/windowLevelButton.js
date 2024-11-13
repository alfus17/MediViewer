document.addEventListener('DOMContentLoaded', () => {
    const renderBarContainer = document.getElementById("renderBarContainer");
    const windowLevelButton = document.getElementById("window-level");

    // 버튼 클릭 시 렌더바 표시/숨기기 토글
    windowLevelButton.addEventListener("click", () => {
        renderBarContainer.style.display = renderBarContainer.style.display === "none" ? "block" : "none";
    });
});

	// 밝기 조절
	document.getElementById("renderBar").addEventListener("input", function () {
	    const renderValue = document.getElementById("renderValue");
	    const brightnessLevel = this.value;
	    renderValue.textContent = brightnessLevel;
	
	    // `windowWidth`와 `windowCenter`를 슬라이더 값에 기반하여 설정
	    const windowWidth = brightnessLevel * 2; 
	    const windowCenter = brightnessLevel * 1;
	
	    // Cornerstone의 viewport 설정 업데이트
	    const viewport = cornerstone.getViewport(element);
	    viewport.voi.windowWidth = windowWidth;
	    viewport.voi.windowCenter = windowCenter;
	    cornerstone.setViewport(element, viewport);
	
	    // 이미지 업데이트
	    cornerstone.updateImage(element);
});