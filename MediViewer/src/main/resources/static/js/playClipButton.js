let playClipInterval; // 자동 재생 간격

// 플레이클립 시작
function startPlayClip() {
    if (imageIds.length === 0) return; // 이미지가 없으면 종료

    // 자동 재생이 이미 진행 중인 경우 중지
    if (playClipInterval) {
        clearInterval(playClipInterval);
    }

    // 현재 이미지 인덱스를 초기화
    currentImageIndex = 0;
    displayImage(currentImageIndex); // 첫 번째 이미지 표시

    // 이미지 자동 재생 간격 설정 (예: 1초)
    playClipInterval = setInterval(() => {
        currentImageIndex++;
        if (currentImageIndex >= imageIds.length) {
            clearInterval(playClipInterval); // 마지막 이미지에 도달하면 자동 재생 중지
            playClipInterval = null; // 간격 초기화
            return; // 함수 종료
        }
        displayImage(currentImageIndex); // 현재 인덱스에 해당하는 이미지 표시
    }, 90); // 1000ms마다 이미지 변경
}

// 플레이클립 중지
function stopPlayClip() {
    if (playClipInterval) {
        clearInterval(playClipInterval); // 재생 중지
        playClipInterval = null; // 간격 초기화
    }
}

// 버튼 클릭 이벤트 설정
document.getElementById('play').addEventListener('click', () => {
    if (!playClipInterval) {
        startPlayClip(); // 자동 재생 시작
    } else {
        stopPlayClip(); // 자동 재생 중지
    }
});
