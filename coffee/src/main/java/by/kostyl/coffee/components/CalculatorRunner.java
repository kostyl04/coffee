package by.kostyl.coffee.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CalculatorRunner {
	@Value("${envfile}")
	private String envpath;
	private String pathToCalculatorClass;

	public PriceCalculator getCalculator()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, URISyntaxException {
		Properties props = new Properties();
		props.load(new FileInputStream(envpath.replace("file:","").replace("\\", "/")));
		pathToCalculatorClass = props.getProperty("calc.class.path");
		PriceCalculator calc = null;
		if (!pathToCalculatorClass.equalsIgnoreCase("standart")) {
			File file = new File(pathToCalculatorClass);
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };
			ClassLoader cl = new URLClassLoader(urls, getClass().getClassLoader());
			Class<?> cls = cl.loadClass(props.getProperty("calc.class.name"));
			calc = (PriceCalculator) cls.newInstance();

		} else
			calc = new MainCalculator();
		return calc;
	}

}
