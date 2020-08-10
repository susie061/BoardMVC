package com.springbook.board.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;

public class MyUtils {
	public static String gensalt() {
		return BCrypt.gensalt();
	}

	public static String hashPassword(String pw, String salt) {
		return BCrypt.hashpw(pw, salt);
	}

//len:길이(0~9사이의 숫자)
	public static String makeRandomNumber(int len) {

		String result = "";

		for (int i = 0; i < len; i++) {
			result += (int) (Math.random() * 10);
		}
		return result;

		/*
		 * int rNum = 0; rNum = Integer.parseInt(result); rNum =(int)Math.random()*9;
		 * 
		 * 
		 * return ;
		 */
	}

	// 리턴값 저장된 파일명
	// "/resources/user/??" 이런 형식으로 사용 할 것이다.
	public static String saveFile(String path, MultipartFile file) {

		String fileNm = null;
		UUID uuid = UUID.randomUUID(); // 중복되지 않은 파일 명 (절대 중복되지 않는 값을 주고 싶을 때), 언더바 시간 값을 줄때 강화

		// 확장자
		String ext = FilenameUtils.getExtension(file.getOriginalFilename()); // 확장자를 알아내기 위해서 , 전체 파일명을 가져오고 확장자만 살리기
		System.out.println("ext : " + ext);

		fileNm = String.format("%s.%s", uuid, ext);
		String saveFileNm = String.format("%s/%s", path, fileNm);

		System.out.println("saveFileNm : " + saveFileNm);
		File saveFile = new File(saveFileNm);
		saveFile.mkdirs(); // 파일이 없다면 폴더를 만들어라

		try {
			file.transferTo(saveFile); // 업로드 파일에 saveFile로 위치로 저장했다.
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
//파일 명만 리턴 해 준다. 랜덤 값이기 때문에 호출 전에는 알 수 없다.
		return fileNm;
	}

	public static boolean deleteFile(String filePath) {
		boolean  result = false;
		File file = new File(filePath);
		if(file.exists()) {
			result = file.delete();
		}
		return result;
	}
}
