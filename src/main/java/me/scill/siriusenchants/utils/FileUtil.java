package me.scill.siriusenchants.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class FileUtil {

	private static final String[] enchantCategories = {"armor", "weapons", "tools"};
	private FileUtil() {}

	public static ArrayList<String> getClassNames(File file, String packageName) {
		packageName = packageName.replaceAll("\\.", "/");
		ArrayList<String> classNames = new ArrayList<>();

		try (final JarFile jarFile = new JarFile(file)) {
			Enumeration<JarEntry> jarEntries = jarFile.entries();

			while (jarEntries.hasMoreElements()) {
				String entryName = jarEntries.nextElement().getName();

				if (entryName.contains("$"))
					continue;

				for (String enchantCategory : enchantCategories) {
					String className = getClassName(entryName, packageName, enchantCategory);
					if (!className.isEmpty())
						classNames.add(className);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return classNames;
	}

	private static String getClassName(String entryName, String packageName, String enchantCategory) {
		packageName += enchantCategory + "/";
		if (entryName.startsWith(packageName) && entryName.length() > packageName.length() + 5)
			return enchantCategory + "." + entryName.substring(packageName.length(), entryName.lastIndexOf('.'));
		return "";
	}
}
