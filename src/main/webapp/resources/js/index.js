/**
 * すべてのbody要素を非表示
 */
function allHide(){
	$("#info").hide();
	$("#chooseDraw").hide();
	$("#compareResult").hide();
	$("#suspendResult").hide();
	$("#finalResult").hide();
}

/**
 * 中断データのロード処理
 */
function doDataLoad(){

	$.ajax({
		contentType: "Content-Type: application/json; charset=UTF-8",
		url: "/DataLoad",
		type: "POST"

	}).done(function(data, status, xhr){
		// 対戦情報の更新
		doInfo();
		// ゲーム継続の選択
		$("#chooseDraw").show();
	});
}

/**
 * 対戦情報の表示処理
 */
function doInfo(){
	$.ajax({
		contentType: "Content-Type: application/json; charset=UTF-8",
		url: "/Info",
		type: "GET"

	}).done(function(data, status, xhr){
		var compeNumber = Number(data.compeNumber);
		var storeCard = Number(data.storeCard);
		var cpuHandSize = Number(data.cpuHandSize);
		var cpuAcuisSize = Number(data.cpuAcuisSize);
		var userHandSize = Number(data.userHandSize);
		var userAcuisSize = Number(data.userAcuisSize);

		var infoHtml = "### 第" + compeNumber + "回戦 ###</br>"
							+ "場に積まれた札:" + storeCard + "枚</br>"
							+ "CPUの持ち札:" + cpuHandSize + "枚,獲得した札:" + cpuAcuisSize + "枚</br>"
							+ "あなたの持ち札:" + userHandSize + "枚,獲得した札:" + userAcuisSize + "枚</br>";
		$("#info").html(infoHtml);

		// 手札がまだ残っていればゲーム継続
		if(cpuHandSize > 0 && userHandSize > 0){
			// ゲーム継続
			$("#info").show();
		}else{
			// 最終結果の処理
			doFinalResult();
		}

	});
}

/**
 * 対戦の処理
 */
function doDraw(){
		// ゲーム継続選択を非表示
		$("#chooseDraw").hide();

		$.ajax({
		contentType: "Content-Type: application/json; charset=UTF-8",
		url: "/Draw",
		type: "GET"

	}).done(function(data, status, xhr){
		var cpuCard = data.cpuCard;
		var userCard = data.userCard;
		var result = data.result;

		var compareResultHtml = "CPUが切った札:" + cpuCard + "</br>"
										+ "あなたが切った札:" + userCard + "</br>"
										+ "</br>";

		switch(result){
			case "win":
				compareResultHtml += "あなたが獲得しました！</br>";
				break;
			case "lose":
				compareResultHtml += "CPUが獲得しました！</br>";
				break;
			case "draw":
				compareResultHtml += "引き分けです。</br>";
				break;
		}

		$("#compareResult").html(compareResultHtml);

		// ゲーム情報の更新
		doInfo();

		// 対戦結果の画面を表示
		$("#compareResult").show();
		// ゲーム継続の確認画面を表示
		$("#chooseDraw").show();

	});
}

/**
 * ゲーム中断処理
 */
function doSuspend(){
	allHide();

	var suspendResultHtml = "";

	$.ajax({
		contentType: "Content-Type: application/json; charset=UTF-8",
		url: "/Suspend",
		type: "GET"

	}).done(function(data, status, xhr){
		var saveResult = data.saveResult;

		if(saveResult){
			suspendResultHtml += "中断データの保存が完了しました。</br>"
		}else{
			suspendResultHtml += "中断データの保存に失敗しました。</br>"
		}

		$("#suspendResultVal").html(suspendResultHtml);
	});

	// データ保存結果を表示
	$("#suspendResult").show();
}

/**
 * 最終結果の処理
 */
function doFinalResult(){
	allHide();

	$.ajax({
		contentType: "Content-Type: application/json; charset=UTF-8",
		url: "/FinalResult",
		type: "GET"

	}).done(function(data, status, xhr){
		var cpuAcuisCard = data.cpuAcuisCard;
		var userAcuisCard = data.userAcuisCard;
		var finalResult = data.finalResult;
		var gameCount = data.gameCount;
		var winCount = data.winCount;
		var maxAquisCardSize = data.maxAquisCardSize;
		var resultCsvSave = data.resultCsvSave;

		var finalResultHtml = "CPUが獲得した札:" + cpuAcuisCard + "枚</br>"
										+ "あなたが獲得した札:" + userAcuisCard + "枚</br>"
										+ "</br>";

		switch(finalResult){
			case "win":
				finalResultHtml += "あなたが勝ちました、おめでとう！<br/>";
				break;
			case "lose":
				finalResultHtml += "CPUが勝ちました、残念・・・<br/>";
				break;
			case "draw":
				finalResultHtml += "引き分けです、惜しい！<br/>";
				break;
		}

		var cumlateResultHtml = "";

		if(resultCsvSave){
			cumlateResultHtml += "ゲーム回数:" + gameCount + "回<br/>"
										+ "勝利回数:" + winCount + "回<br/>"
										+ "最大カード枚数:" + maxAquisCardSize + "枚<br/>"
		}else{
			cumlateResultHtml += "対戦成績の読み込みに失敗しました。"
		}

		$("#finalResultVal").html(finalResultHtml);
		$("#cumlateResultVal").html(cumlateResultHtml);

		// 対戦結果の画面を表示
		$("#compareResult").show();
		// 最終結果を表示
		$("#finalResult").show();

	});
}

/**
 * ページ読み込み時の処理
 */
$(function(){
	allHide();

	// 中断データのロード選択
	$("#dataLoad").show();

	// 中断データロードの選択時
	$("#submitDataLoad").click(function(){
		var fileLoad = $("input[name=fileLoad]:checked").val();

		if(fileLoad == "yes"){
			// 中断データのロード処理
			doDataLoad();
		}else{
			// 対戦情報の読み込	み
			doInfo();
			// ゲーム継続の選択
			$("#chooseDraw").show();
		}

		// 中断データロードの選択画面を非表示
		$("#dataLoad").hide();
	});

	// ゲーム継続の確定時
	$("#submitDraw").click(function(){
		var drawOrSuspend = $("input[name=drawOrSuspend]:checked").val();

		if(drawOrSuspend == "draw"){
			// 対戦
			doDraw();
		}else{
			// ゲーム中断
			doSuspend();
		}
	});
});