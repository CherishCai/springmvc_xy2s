

package com.controller;

import com.util.VeDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping(value = "/image.shtml")
	public String upload(@RequestParam(value = "image", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {

		String path = request.getSession().getServletContext().getRealPath("/upfiles");
		System.out.println("path = " + path);
		String fileName = file.getOriginalFilename();
		int i = fileName.lastIndexOf(".");
		String name = String.valueOf(VeDate.getStringDatex());
		String type = fileName.substring(i + 1);
		fileName = name + "." + type;

		File targetDirectoryPath = new File(path);
		if (!targetDirectoryPath.exists()) {
			targetDirectoryPath.mkdirs();
		}
		// 保存
		try {
			file.transferTo(new File(path, fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("imageFileName", fileName);
		return "/saveimage";
	}
}
