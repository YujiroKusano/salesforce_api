# WSCの作成
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
    1. ```[設定]```からクイック検索で```API```を検索インテグレーションの```API```を選択。
    2. ダウンロード対象のリンクを右クリックし、```リンク先を別名で保存```をクリックして保存。
    3. ダウンロードした```partner.wsdl```を```libs/```フォルダに移動。

4. ```partner.wsdl```から```partner.jar```の作成
    ```bath
    $ cd libs/
    $ java -classpath force-wsc-56.0.0.jar:ST4-4.3.jar:antlr-runtime-3.5.2.jar com.sforce.ws.tools.wsdlc partner.wsdl partner.jar
    ```

# サンプルアプリケーションの作成
2. 
