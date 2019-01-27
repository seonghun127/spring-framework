//null check
function isEmpty(value) {
	if (!value) {
		return true;
	}
	if (value) {
		console.log("value:" + !value.trim().length);
		return !value.trim().length;
	}
	for ( var key in value) {
		if (hasOwnProperty.call(value, key)) {
			return false;
		}
	}
	return true;
}

//email check
function fnCheckEmail(email) {
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	if (!regExp.test(email)) {
		alert('email형식을 확인하세요.');
		return false;
	}
	return true;
}

//숫자 check
function fnCheckNum(num) {
	var regExp = /^[0-9]+$/;
	if (!regExp.test(num)) {
		alert('형식을 확인하세요.\n(숫자만 입력 가능)');
		return false;
	}
	return true;
}