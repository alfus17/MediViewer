function testDetailAxios(studyKey) {
    console.log("testDetailAxios_studyKey : ", studyKey);
    axios.get(`/api/views/${studyKey}`).then(result => {
        console.log(result.data);
        series.push(...result.data);

        // URL 배열 생성
        const urlList = series.map(seriesKey => `/api/views/${studyKey}/${seriesKey}`);
        console.log(" urlList : ", urlList);

        // 병렬로 모든 URL에 대해 axios GET 요청 보내기
        // 모든 요청 완료 후 처리
        axios.all( urlList.map(url => axios.get(url)) ).then(axios.spread((...responses) => {
            const dataList = responses.map(response => response.data);
            dcmPathLists.push(...dataList);

            console.log("dcmPathLists [] :  ", dcmPathLists);
        })).catch(errors => {
            console.error("Error in axios.all: ", errors);
        });
        
    });
}


async function test (studyKey) {
    try {
        console.log("testDetailAxios_studyKey : ", studyKey);
        
        // 첫 번째 요청
        const result = await axios.get(`/api/views/${studyKey}`);
        console.log(result.data);
        series.push(...result.data);

        // URL 배열 생성
        const urlList = series.map(seriesKey => `/api/views/${studyKey}/${seriesKey}`);
        console.log("urlList : ", urlList);

        // 병렬 요청 호출
        await fetchSeriesData(urlList);
        
    } catch (error) {
        console.error("Error in testDetailAxios: ", error);
    }
}

async function fetchSeriesData(urlList) {
    try {
        // 병렬로 모든 URL에 대해 axios GET 요청 보내기
        const responses = await Promise.all(urlList.map(url => axios.get(url)));

        const dataList = responses.map(response => response.data);
        dcmPathLists.push(...dataList);

        console.log("dcmPathLists [] : ", dcmPathLists);
        
    } catch (error) {
        console.error("Error in fetchSeriesData: ", error);
    }
}