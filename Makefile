compile:
	javac \
		--module-path /usr/share/openjfx/lib \
		--add-modules javafx.controls,javafx.fxml,javafx.media \
		-encoding SJIS \
		*.java

run:
	java \
		--module-path /usr/share/openjfx/lib \
		--add-modules javafx.controls,javafx.fxml \
		MapGame

clean:
	rm *.class