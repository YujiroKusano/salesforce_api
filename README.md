
# Partner WSDLを使用したプロジェクトの作成方法
> 参考サイト：[Java 開発者環境の設定](https://developer.salesforce.com/docs/atlas.ja-jp.salesforce_developer_environment_tipsheet.meta/salesforce_developer_environment_tipsheet/salesforce_developer_environment_overview.htm)
## 環境
 OS: macOS Monterey ver12.5.1

## 前提
1. javaがダウンロードされていること
    > [Javaダウンロード](https://www.oracle.com/java/technologies/downloads/)
2. Mavenがダウンロードされていること<br>
- インストール方法
    - Windows:  [ダウンロードサイト](https://maven.apache.org/)<br>
    - macOS:    コマンド
        ```bath
        $ brew install maven
        ```
        ```bath
        $ vi ~/.zprofile

        # M2_HOMEを作成
        export M2_HOME=/usr/local/Cellar/maven/3.8.1/libexec/<br>
        # PATHの先頭に追加
        export PATH=$M2_HOME/bin:$PATH
        ```
3. 以下のコマンドが起動すること
    ```bath
    $ mvn -v
    Apache Maven 3.8.6 (xxxxxxxxxxxxxxxxxxxxxxxxxxxxx)
    Maven home: /usr/local/Cellar/maven/3.8.6/libexec
    Java version: 18.0.2, vendor: Homebrew, runtime: /usr/local/Cellar/openjdk/18.0.2/libexec/openjdk.jdk/Contents/Home
    Default locale: ja_JP, platform encoding: UTF-8
    OS name: "mac os x", version: "12.5.1", arch: "x86_64", family: "mac"
    ```

## プロジェクトの作成
1. 以下のコマンドにてプロジェクトを作成
    ```bath
    $ mvn archetype:generate \
        -DarchetypeGroupId=com.wsc \
        -DarchetypeArtifactId=wsc \
        -DarchetypeVersion=1.0
    ```
## ライブラリ(jarファイル)のダウンロード
1. ```libs/```フォルダをプロジェクト内部に作成
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
2. 以下をpomファイルの```<dependencies>```に追記
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