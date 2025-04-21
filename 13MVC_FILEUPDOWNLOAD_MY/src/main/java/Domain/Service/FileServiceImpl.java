package Domain.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import file.Properties;

public class FileServiceImpl {

	// 싱글톤 패턴
	private static FileServiceImpl instance;

	private FileServiceImpl() throws Exception {

		System.out.println("[SERVICE] FileServiceImpl init...");
	};

	public static FileServiceImpl getInstance() throws Exception {
		if (instance == null)
			instance = new FileServiceImpl();
		return instance;
	}

	public boolean upload(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		LocalDateTime now = LocalDateTime.now();
		// yyyyMMdd_HHmmss 폴더명
		String folderName = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

		// 업로드 경로 설정
		String UploadPath = Properties.ROOT_PATH + File.separator // \\구분자
				+ Properties.UPLOAD_PATH + File.separator + folderName + File.separator;

		// 디렉토리 생성( c:\\upload\\202504211_102933\\)
		File dir = new File(UploadPath);
		if (!dir.exists())
			dir.mkdirs();

		// 파일 업로드
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {

			System.out.println("PART's NAME : " + part.getName());
			System.out.println("PART's SIZE : " + part.getSize());
			String contentDisp = part.getHeader("content-disposition"); // form-data, name, file명저장됨
			String[] tokens = contentDisp.split(";"); // contentDisp에 있는 값 ;기준으로 배열로저장
			String filename = tokens[2].trim().substring(10, tokens[2].trim().length() - 1); // 0,1,2중에 2 = file명 파일명부분만
																								// 골라내서 저장
			System.out.println("contentDisp : " + contentDisp);
			System.out.println("tokens[2] : " + tokens[2]);
			System.out.println("filename : " + filename);

			part.write(UploadPath + filename); //

		}
		return false;
	}

	public Map<String, List<File>> getFileList() {
		Map<String, List<File>> map = new LinkedHashMap();
		// 업로드 경로 설정
		String UploadPath = Properties.ROOT_PATH + File.separator // \\구분자
				+ Properties.UPLOAD_PATH;

		File dir = new File(UploadPath);
		if (dir.exists() && dir.isDirectory()) { // 디렉토리가존재하고 경로가 파일등이아니라 폴더이면
			File[] folders = dir.listFiles(); // 폴더찾기
//			Arrays.stream(folders).forEach(System.out::println);	

			for (File folder : folders) {
				File[] list = folder.listFiles(); // 폴더 내 파일
				System.out.println("DIR : " + folder.getName());
				Arrays.stream(list).forEach(System.out::println);
				map.put(folder.getName(), Arrays.stream(list).collect(Collectors.toList()));
			}
			// 폴더 : 파일리스트
		}

		return map;
	}

	public boolean download(HttpServletRequest req, HttpServletResponse response) throws IOException {
		String folder = req.getParameter("folder");
		String filename = req.getParameter("filename");
		System.out.println("FILENAME : " + filename + "folder : " + folder);

		String downloadPath = ""; // ROOTPATH(c:) + / + UPLOAD_PATH + / + FOLDER + / + FILENAME
		downloadPath = Properties.ROOT_PATH + File.separator + Properties.UPLOAD_PATH + File.separator + folder
				+ File.separator + filename;

		// 내 래포지토리에
		// JSP_SERVLE/01JSP_MY/src/main/webapp/C05/files/04Downloade_SingleFile.jsp
		response.setHeader("Content-Type", "application/octet-stream;charset-utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "")); // replaceAll :공백 제거
//		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8")); // 공백에 + 들어감..;;

		FileInputStream in = new FileInputStream(downloadPath);
		ServletOutputStream bout = response.getOutputStream();

		byte[] buff = new byte[4096];
		while (true) {
			int data = in.read(buff);
			if (data == -1) {
				break;
			} else {
				bout.write(buff, 0, data);
				bout.flush();
			}
		}

		bout.close();
		in.close();

		return false;
	}
}
