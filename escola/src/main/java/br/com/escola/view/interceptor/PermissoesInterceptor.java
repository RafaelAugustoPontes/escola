package br.com.escola.view.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.escola.controller.exception.UnauthorizedException;
import br.com.escola.model.entidades.PerfilModel;
import br.com.escola.model.entidades.UsuarioModel;
import br.com.escola.model.repository.UsuarioRepository;

@RequestScope
@Component
public class PermissoesInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UsuarioModel usuario = repository.findByLogin(authentication.getName());
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			if (method.getDeclaringClass().isAnnotationPresent(Administrador.class)) {
				if (usuario.getPessoa().getPerfil().equals(PerfilModel.ADMINISTRADOR))
					return true;
				else
					throw new UnauthorizedException("Acesso não autorizado");
			} else {
				return true;
			}
		}
		return true;
	}

}
