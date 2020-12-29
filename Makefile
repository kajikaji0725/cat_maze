compile:
	javac \
		--module-path C:/Users/cs20020/Downloads/javafx-sdk-11.0.2/lib \
		--add-modules javafx.controls,javafx.fxml,javafx.media \
		-encoding SJIS \
		*.java

run:
	java \
		--module-path C:/Users/cs20020/Downloads/javafx-sdk-11.0.2/lib \
		--add-modules javafx.controls,javafx.fxml \
		MapGame

clean:
	rm *.class