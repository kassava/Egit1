package com.test.shiz;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Legion {
		// TODO добавить - в числа.
	public static void main(String[] args) {
		System.out.println("In nomine Domini nostri Legion!");
		
		System.out.println(args.length);
		System.out.println(args[0]);
		System.out.println(args[1]);
		
		final String inputFileName = args[0];
		final String outputFileName = args[1];
		
		// Проверка корректности имён файлов.
		if (isFileNameCorrect(inputFileName) && isFileNameCorrect(outputFileName)) {
			System.out.println("Имена файлов корректны");
		} else {
			System.out.println("Имена файлов некорректны.");
			return;
		}
		
		// Считывание строк из входного файла
		List<String> strings = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
		} catch (FileNotFoundException e1) {
			System.out.println("Входной файл не найден.");
			return;
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				strings.add(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		 
		// Проверка N (количества строк в файле).
		if (strings.size() < 1 || strings.size() > 100) {
			System.out.println("Ошибка. Число N (количество строк) за пределами допустимого интервала (1 <= N <= 100).");
		}
		
		// Проверка корректности строк.
		for (int i = 0; i < strings.size(); i++) {
			if (!isLineCorrect(strings.get(i))) {
				System.out.println("Строка " + i + " некорректна. Конец работы.");
				System.out.println(strings.get(i));
				return;
			}
		}
		
		// Сбор чисел.
		ArrayList<Point> points = new ArrayList<Point>();
		Pattern pattern = Pattern.compile("-?\\d+");
		Matcher matcher = null;
		for (int i = 0; i < strings.size(); i++) {
			matcher = pattern.matcher(strings.get(i));

			while (matcher.find()) {
				Point point = new Point();
				point.x = Integer.parseInt(matcher.group());
				matcher.find();
				point.y = Integer.parseInt(matcher.group());
				System.out.println(point.x + " - " + point.y);	
				points.add(point);
			}			
		}
		System.out.println("All points: " + points.size());
		
		// Определение границ области. 
		int minX = points.get(0).x, 
				minY = points.get(0).y, 
				maxX = points.get(0).x, 
				maxY = points.get(0).y;
		for (Point point : points) {
			maxX = Math.max(maxX, point.x);
			maxY = Math.max(maxY, point.y);
			minX = Math.min(minX, point.x);
			minY = Math.min(minY, point.y);
		}
		System.out.println(maxX + " - " + maxY + " " + minX + " - " + minY);
		
		// Подсчёт площади.
		int s = 0;
		for (int i = minX; i < maxX; i++) {
			for (int j = minY; j < maxY; j++) {
				for (int rectIdx = 0; rectIdx < points.size(); rectIdx = rectIdx + 2) {
					Point p1 = points.get(rectIdx);
					Point p2 = points.get(rectIdx + 1);
					
					if (i >= p1.x && (i + 1) <= p2.x && j >= p1.y && (j + 1) <= p2.y) {
						s++;
						break;
					}
				}
			}
		}
		System.out.println("Площадь = " + s);
	}
	
	public static boolean isLineCorrect(String line) {
		Pattern pattern = Pattern.compile("^(-?[0-9]|-?[1-9][0-9]|-?[1-9][0-9][0-9]|-?[1-9][0-9][0-9][0-9]|10000)[ ]+" +
				"(-?[0-9]|-?[1-9][0-9]|-?[1-9][0-9][0-9]|-?[1-9][0-9][0-9][0-9]|10000)[ ]+" +
				"(-?[0-9]|-?[1-9][0-9]|-?[1-9][0-9][0-9]|-?[1-9][0-9][0-9][0-9]|10000)[ ]+" +
				"(-?[0-9]|-?[1-9][0-9]|-?[1-9][0-9][0-9]|-?[1-9][0-9][0-9][0-9]|10000)$");
		Matcher matcher = pattern.matcher(line);
		return matcher.find();
	}
	
	public static boolean isFileNameCorrect(String name) {		 
        Pattern pattern = Pattern.compile("(.+)?[><\\|\\?*/:\\\\\"](.+)?");
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }
}
