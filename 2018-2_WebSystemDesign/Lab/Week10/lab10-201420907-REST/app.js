var express =require('express');
const bodyParser = require('body-parser');

const app = express();
const router = express.Router();

app.use((req, res, next) =>{
    res.header("Access-Control-Allow-Origin", "*");
    res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE, OPTIONS');
    res.header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
    next()
});

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

const objList = [
    {
        id: 0,
        title: "애플 워치",
        content: "애플 워치(영어 : Apple Watch)은 애플이 판매하는 스마트 워치로, 2014년 9월 9일에 미국 캘리포니아\n" +
                 "쿠퍼티노 디앤자 단과대학 플린트센터에서 발표했다. 런칭 제품으로는 화면 1.5 인치 제품과 1.65 인치 제품으로\n" +
                 "나뉘고, 이를 또 다시 애플 워치(Apple Watch), 애플 워치 스포츠(Apple Watch Sport), 애플 워치 에디션(App\n" +
                 "le Watch Edition)으로 나뉜다. [1] 2015년 3월 9일 미국 샌프란시스코 에바부에나 센터에서 열린 공식 행사에서\n" +
                 "애플 워치에 대한 정보를 추가적으로 발표했다. 2015년 4월 24일에 1차 출시국에서 판매하며, 대한민국의 애플워치\n" +
                 "출시일은 6월 26일이다. [2] 2015년 4월 25일 애플 워치용 전용 앱스토어가 개시되었고 예약주문도 시작되었다. 일\n" +
                 "부 명품매장에선 직접 구매도 가능하다."
    },
    {
        id: 1,
        title: "아이폰",
        content: "아이폰(영어: iPhone)은 미국의 애플사의 휴대전화 브랜드 및 시리즈이다. 2007년 1월 9일에 스티브 잡스가 처음으로\n" +
            "발표를 하였다.아이폰은 세계 휴대전화 수익의 103.6%를 차지하는 모델이다. 2016년 7월 28일에는 휴대전화 최초로\n" +
            "누적판매량 10억대를 돌파했다.아이폰의 초기 모델인 아이폰 2G[주 1] 는 2007년 6월 29일 오후6시[1] 미국 AT&T 대리점\n" +
            "과 애플 매장에서 최초로 판매가 시작되었다. 출시 초기에는 4GB 모델과 8GB 두 가지 모델이 출시되었으나, 2007년 9월 \n" +
            "5일부터 4GB 모델이 단종되고 8GB 모델이 99달러에 판매되었다.그 후 2008년 7월 11일, 기존 아이폰에 비해 가격이 저렴\n" +
            "해지고, 용량이 커졌으며, 3세대 통신망에 대응하는 아이폰 3G가 미국에서 출시되었고,[2] 2009년 6월 8일, 더 빠른 CPU와 \n" +
            "아이폰 OS[주 2] 3.0을 기본으로 채용한 아이폰 3GS가 미국 WWDC에서2010년 6월 8일, iOS 4를 채용해 멀티태스킹 기능 \n" +
            "등을 탑재한 아이폰 4가 정식으로 발표되었고, 2011년 10월 4일, iOS 5 버전과 시리를 처음으로 탑재했던 아이폰 4S가 공식적으로 \n" +
            "발표되고,한국에 처음으로 판매되었다. 2012년 9월 12일에 아이폰 최초로 4인치 스크린을 탑재한 아이폰 5가 공식적으로 발표되\n" +
            "었으며, 2013년 9월 11일에 5가지 색상의 폴리카보네이트 소재로 된 아이폰 5C와 지문인식센서(Touch ID)가 탑재된 아이폰 5S가 \n" +
            "공식적으로 발표되었다."
    },
    { id : 2, title: "HELLO_2", content: "ABCDEDLKJFASFLKJ"},
    { id : 3, title: "HELLO_3", content: "ABCDEDLKJFASFLKJ"},
    { id : 4, title: "HELLO_4", content: "ABCDEDLKJFASFLKJ"},
    { id : 5, title: "HELLO_5", content: "ABCDEDLKJFASFLKJ"},
    { id : 6, title: "HELLO_6", content: "ABCDEDLKJFASFLKJ"},
    { id : 7, title: "HELLO_7", content: "ABCDEDLKJFASFLKJ"},
];

// 전체 게시글 리스트를 반환한다.
app.get('/post', (req, res) => {
    console.log("/post get!");

    res.json(objList)
});

// postId 를 id 로 갖는 게시글을 반환한다.
app.get('/post/:postId', (req, res) => {
    console.log("/post/:postId get!");
    var postId = parseInt(req.params.postId);

    for(let i=0; i<objList.length; i++){

        if(objList[i].id === postId) {
            console.log("send");
            res.send(objList[i]);
        }
        if(i === (objList.length-1))
            res.end();
    }
});

app.listen(8000, (err) => {
    console.log('SYSTEM : HTTP SERVER RUNNING ON 8000 PORT');
});