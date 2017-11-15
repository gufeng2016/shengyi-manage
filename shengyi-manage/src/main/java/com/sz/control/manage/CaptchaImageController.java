package com.sz.control.manage;

import com.sz.common.util.AppConstant;
import com.sz.common.util.CaptchaCode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * User: xin.fang
 * Date: 14-06-04
 * Time: 上午 10:30
 * 生成验证码图片的controller
 */
@Controller
public class CaptchaImageController {

	private Logger logger = Logger.getLogger(CaptchaImageController.class);

	@RequestMapping(value = "/captcha")
	public void captchaImage(HttpServletRequest request, HttpServletResponse response) {
		ServletOutputStream out = null;
		try {
			response.setDateHeader("Expires", 0);
			// Set standard HTTP/1.1 no-cache headers.
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			// Set standard HTTP/1.0 no-cache header.
			response.setHeader("Pragma", "no-cache");
			// return a jpeg
			response.setContentType("image/jpeg");
			// create the image
			CaptchaCode captchaCode = new CaptchaCode();
			BufferedImage image = captchaCode.creatImage();
			// store the text in the session
			request.getSession().setAttribute(AppConstant.KAPTCHA_SESSION_KEY, captchaCode.getSRand());
			out = response.getOutputStream();
			// write the data out
			ImageIO.write(image, "jpg", out);
			out.flush();
		} catch (Exception e) {
			logger.error("获取验证码图片出现异常", e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				logger.error("获取验证码图片时关闭流出现异常", e);
			}
		}
	}

}
