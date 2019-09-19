package br.com.escola.view.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracaoInterceptor implements WebMvcConfigurer {

	@Autowired
	private PermissoesInterceptor permissoes;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissoes);
	}

}
