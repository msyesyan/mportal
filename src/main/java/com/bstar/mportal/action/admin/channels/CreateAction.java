package com.bstar.mportal.action.admin.channels;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.web.bind.ServletRequestUtils;

import com.bstar.mportal.model.Category;
import com.bstar.mportal.model.Channel;

@Action
@Results({
	@Result(
		type="redirect", location="/admin/channels/"
	),
	@Result(
		name="input", location="/WEB-INF/views/admin/channels/new.jsp"
	)
})
public class CreateAction extends NewAction {
	private File logo;
	private String logoFileName;

	private static final long serialVersionUID = -4937201174820582195L;

	
	public void validate() {
		Channel channel = channelService.findByNum(this.channel.getNum());
		if (channel != null) {
			addFieldError("channel.num", "频道号必须唯一");
		}
	}
	
	public String execute() throws Exception {
		int[] categoryIds = ServletRequestUtils.getIntParameters(request,
				"categoryIds");
		List<Category> categories = categoryService.findByIds(categoryIds);
		//if(channelService.findByName(channel.getNum())!=null)
			
		channel.setCategories(categories);
		channelService.save(channel);
		channelService.upload(logo, servletContext, channel, logoFileName);
		return "success";
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

}
