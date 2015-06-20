
package org.mybatis.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 
 * @author 田广文
 * @date 2011-12-31 上午12:00:21
 */
public class ExampleGenerator
{
	public static void main(String[] args) throws Exception
	{
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("src/main/generator/org/mybatis/generator/umappConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings)
		{
			System.out.println(warning);
		}
	}
}
