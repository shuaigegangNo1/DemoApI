package com.sgg.rest.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;
 
public class ImgToolsTest {
 
	@Test
	public void testCompressUnderSize() throws IOException {
		byte[] data = ImgTools.compressUnderSize(readInByteArray(new File("e:\\wx.jpg")), 400 * 300);
		Assert.assertTrue(data.length <  2560 * 1600);
		FileUtils.writeByteArrayToFile(new File("e:\\compressed.jpg"), data);
		File file =new File("e:\\wx.jpg");
		Thumbnails.of(file) 
        .scale(0.5f) 
        .outputQuality(0.5f) 
        .toFile("e:\\compressed2.jpg");
	}
 
	private byte[] readInByteArray(File imgFile) {
		try {
			return IOUtils.toByteArray(new FileInputStream(imgFile));
 
		} catch (IOException e) {
			throw new IllegalStateException("读取待压缩图片过程中出错，请及时联系管理员！", e);
 
		}
	}
}
