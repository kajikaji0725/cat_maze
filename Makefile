compile:
	javac \
<<<<<<< HEAD
		--module-path C:/Users/cs20020/Downloads/javafx-sdk-11.0.2/lib \
=======
		--module-path /usr/share/openjfx/lib \
>>>>>>> 83787f995ee14eea33de6583304674195a30030f
		--add-modules javafx.controls,javafx.fxml,javafx.media \
		-encoding SJIS \
		*.java

run:
	java \
<<<<<<< HEAD
		--module-path C:/Users/cs20020/Downloads/javafx-sdk-11.0.2/lib \
=======
		--module-path /usr/share/openjfx/lib \
>>>>>>> 83787f995ee14eea33de6583304674195a30030f
		--add-modules javafx.controls,javafx.fxml \
		MapGame

clean:
	rm *.class