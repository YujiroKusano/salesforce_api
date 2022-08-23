
# WSCの作成
## 前提
1. Mavenがダウンロードされていること
2. 以下のコマンドが起動すること
    ```bath
    $ mvn -v
    Apache Maven 3.8.6 (xxxxxxxxxxxxxxxxxxxxxxxxxxxxx)
    Maven home: /usr/local/Cellar/maven/3.8.6/libexec
    Java version: 18.0.2, vendor: Homebrew, runtime: /usr/local/Cellar/openjdk/18.0.2/libexec/openjdk.jdk/Contents/Home
    Default locale: ja_JP, platform encoding: UTF-8
    OS name: "mac os x", version: "12.5.1", arch: "x86_64", family: "mac"
    ```
3. 
## プロジェクトの作成
1. 以下のコマンドにてプロジェクトを作成
    ```bath
    $ mvn archetype:generate \
        -DarchetypeGroupId=com.wsc \
        -DarchetypeArtifactId=wsc \
        -DarchetypeVersion=1.0
    ```
## ライブラリ(jarファイル)のダウンロード
1. ```libs/```フォルダを作成
    ```bath
    $ mkdir libs
    ```
2. 下記のライブラリをダウンロードし```libs/```フォルダに移動
    - [Force WSC](https://mvnrepository.com/artifact/com.force.api/force-wsc)
    - [ANTLR 3 Runtime](https://mvnrepository.com/artifact/org.antlr/antlr-runtime)
    - [StringTemplate 4](https://mvnrepository.com/artifact/org.antlr/ST4)

3. 必要なWSDLファイルを取得 ※例としてPartner(パートナーWSDLの生成)を選択
    <img src="images/スクリーンショット 2022-08-23 15.42.19.png" alt="WSDL" title="WSDLダウンロード画面">
    > 1. ```[設定]```からクイック検索で```API```を検索インテグレーションの```API```を選択。
    > 2. ダウンロード対象のリンクを右クリックし、```リンク先を別名で保存```をクリックして保存。(Macの場合)
    > 3. ダウンロードした```partner.wsdl```を```libs/```フォルダに移動。
4. ```partner.wsdl```から```partner.jar```の作成
    ```bath
    $ cd libs/
    $ java -classpath force-wsc-56.0.0.jar:ST4-4.3.jar:antlr-runtime-3.5.2.jar com.sforce.ws.tools.wsdlc partner.wsdl partner.jar
    ```
## ライブラリ(jarファイル)の読み込み
### ```partner.jar```の読み込み
1. 以下のmvnのコマンドにて生成したライブラリ```partner.jar```をプロジェクトに読み込ませる
    ```bath
    mvn install:install-file -Dfile=libs/partner.jar -DgroupId=com.sforce.soap.partner -DartifactId=partner -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
    ```
2. 以下をpomファイルの```<dependencies>に追記
    ```pom.xml
    <dependency>
      <groupId>com.sforce.soap.partner</groupId>
      <artifactId>partner</artifactId>
      <version>1.0</version>
    </dependency>
    ```
### ```force-wsc-XX.X.X.jar```の読み込み
1. 以下のmvnのコマンドにて生成したライブラリ```force-wsc-XX.X.X.jar```をプロジェクトに読み込ませる
    ```
    mvn install:install-file -Dfile=libs/force-wsc-56.0.0.jar -DgroupId=co.ws -DartifactId=ws -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
    ```