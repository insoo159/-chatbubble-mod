# Chat Bubble（聊天泡泡）Fabric 模組

把 Minecraft Java 版的聊天訊息，改成類似漫畫對話框的樣式顯示：
白底黑框的泡泡、下方有小尾巴、尾巴下面顯示玩家名稱（依名字自動配色）。

目前設定為 **Minecraft 1.21.8 + Fabric**。這是本文撰寫時最新、且仍使用
傳統 Yarn mappings 工具鏈的穩定版本（Minecraft 從 26.1 這個版本開始改用
新的年份命名方式，並改用 Mojang 官方 mappings、Yarn 不再是官方支援對象，
工具鏈整套都不一樣，所以先用 1.21.8 打底，最穩、教學資源也最多）。

## 這個模組怎麼運作

- `ChatHudMixin`：關閉遊戲原本聊天視窗的畫面繪製（打字、送出訊息完全不受影響）。
- `ChatBubbleClient`：監聽「收到聊天訊息」「收到系統訊息」事件，把訊息存進
  `BubbleChatManager`。
- `BubbleRenderer`：每一幀把還沒淡出的訊息畫成泡泡，疊在畫面左下角
  （快捷欄上方）。

## 如何建置 / 執行（在你自己的電腦上）

這個沙盒環境沒有網路存取 Minecraft / Fabric 的 Maven 伺服器，
所以沒辦法在這裡直接幫你編譯。請照以下步驟在你自己電腦上跑：

1. 安裝 **JDK 21**。
2. 安裝 **IntelliJ IDEA**（社群版即可）+ 裡面的
   **Minecraft Development** 外掛程式（可省略，但很方便）。
3. 把這個資料夾整個複製到你的電腦，直接用 IDEA「Open」開啟這個資料夾
   （它會自動偵測到 `build.gradle` 並問你要不要 Import Gradle 專案，選是）。
   - 如果你沒有用 IDEA，也可以在終端機執行：
     ```
     gradle wrapper
     ./gradlew genSources
     ./gradlew runClient
     ```
4. 第一次匯入會下載 Minecraft 本體、Yarn mappings、Fabric API，需要一點時間。
5. 執行 Gradle 任務 `runClient`（IDEA 右側 Gradle 面板 → Tasks → fabric →
   runClient），會開一個開發用的 Minecraft 視窗，加入伺服器或開單人世界
   試試聊天室的樣子。
6. 確定沒問題後，執行 `./gradlew build`，會在 `build/libs/` 產生
   `chatbubble-1.0.0.jar`，把它丟進正式版 Minecraft 的 `mods` 資料夾
   （需要先裝好 Fabric Loader 和對應版本的 Fabric API mod）即可遊玩。

## 想調整外觀

- 顏色、留白、尾巴形狀都在 `BubbleRenderer.java` 開頭幾個常數，
  改數字重新編譯就能看到效果。
- 訊息停留多久才開始淡出、淡出要多久，在 `BubbleChatManager.java`
  的 `VISIBLE_MILLIS` / `FADE_MILLIS`。
- 想換版本（例如之後要跟到 1.21.9、1.21.10…），改 `gradle.properties`
  裡的四個版本號即可，程式碼幾乎不用動。
