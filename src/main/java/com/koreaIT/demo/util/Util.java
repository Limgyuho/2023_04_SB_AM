package com.koreaIT.demo.util;

public class Util {
	public static boolean empty(Object obj) {
		
		if (obj == null) {
			return true;
		}
		
//		if (obj instanceof String == false) {
//			return true;
//		}
		
		String str = (String) obj;
		
		return str.trim().length() == 0;
	}

	public static String f(String format, Object... args) {
		return String.format(format, args);
	}

	
	
	//수정 삭제를 하였을때 리절트 데이터가 나오는것이 아니라 
	//뒤로 돌아가기 위해서 매서드를 생성 한다 삭제수정이 완료후 리스트로 돌아간다
	
	//보여줄 메세지가 없을때 수정이나 삭제가 잘 되아 않았을경우
	
	public static String jsHistoryBack(String msg) {
	
		//문제가 있다면 컨드롤러로 간다
		if (msg == null) {
			msg = "";
		}
		//실제로 수정 삭제가 되지 않을 경우 이 메서드자체가 성공할경우
		//스크립트로 틀을 짜준다 실제로 메세지가 보여주는 곳은 컨트롤러의 리턴 부분이다
		return Util.f("""
					<script>
						const msg = '%s'.trim();
						if (msg.length > 0) {
							alert(msg);
						}
						history.back();
					</script>
					""", msg);
	}

	//뒤로 돌아가기 위해서 매서드를 생성 한다 삭제수정이 완료후 리스트로 돌아간다
	//수정이나 삭제를 하였을때 성공 했다는 메세지를 보여주기 위함이다
	public static String jsReplace(String msg, String uri) {
		
		if (msg == null) {
			msg = "";
		}
		
		if (uri == null) {
			uri = "";
		}
		
		//스크립트로 틀을 짜준다 실제로 메세지가 보여주는 곳은 컨트롤러의 리턴 부분이다
		return Util.f("""
				<script>
					const msg = '%s'.trim();
					if (msg.length > 0) {
						alert(msg);
					}
					location.replace('%s');
				</script>
				""", msg, uri);
	}
}