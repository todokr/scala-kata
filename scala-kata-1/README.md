# KATA01 - Discount Logic of Electronic Toll Collection System

高速道路のETC割引の計算ロジックを実装します。
- ただし、平日朝夕割引は実際には後日還元なのですが、ここでは他の割引と同じく即時適用かつ走行距離による還元率の変化はないものとします。
- 走行記録は、24時間を超えないものとします。

## 業務ルール
割引には以下の種類と、適用できる条件があります。
以下の割引のうち、 **最も割引率が高い割引** が適用されます。

### 平日朝夕割引

#### 割引対象車種
- すべての車種

#### 割引対象エリア
- 地方部

#### 割引対象日時

![平日朝夕割引](./images/morning_or_evening.png)

- 平日かつ、朝「6:00〜9:00」、あるいは夕「17:00〜20:00」に、 **入口または出口料金所を通過**する
  - 入: 6:00、出: 9:00  => OK（入/出の両方が朝の時間内）
  - 入: 6:00、出: 9:01  => OK（入が朝の時間内）
  - 入: 5:59、出: 9:00  => OK（出が朝の時間内）
  - 入: 6:00、出: 17:00 => OK（入が朝時間内、出が夕時間内）
  - 入: 5:59、出: 9:01  => NG（入/出ともに朝の時間外）

#### 割引率
当月の高速道路利用回数によって変動します。

- 0〜4回: 0%割引
- 5回〜9回: 30%割引
- 10回以上: 50%割引

### 休日割引

#### 割引対象車種
- 普通車
- 軽自動車等
- 二輪車

#### 割引対象エリア
- 地方部

#### 割引対象日時

![休日割引](./images/holiday.png)

- 土曜・日曜・祝日

#### 割引率
- 30%割引

### 深夜割引

#### 割引対象車種
- すべての車種

#### 割引対象エリア
- すべて

#### 割引対象日時

![深夜割引](./images/midnight.jpg)

- 0:00〜4:00内に高速道路を走行する
  - 入: 0:00、出: 4:00 => OK
  - 入: 0:00、出: 4:01 => OK
  - 入: 23:59、出: 4:00 => OK
  - 入: 23:59、出: 4:01 => OK
  - 入: 23:50、出: 23:59 => NG
  - 入: 4:01、出: 4:10 => NG

#### 割引率
- 30%割引

## 問題

上記の業務ルールに従って、割引率を計算する `DiscountCalculator` を、
 **割引のルールが増えたとしても理解しやすく、メンテナンスが楽なように** 実装してください。

classやtrait、objectなどを新しく作ったり、既存のmodelにメソッドを新しく追加してOKです。

```scala
class DiscountCalculator {
    def calc(drive: HighwayDrive): DiscountPercentage = ???
}
```

## 既存のコードについて

- DiscountCalculator: これを実装する

### model パッケージ
- Driver: ドライバー。「今月に何回高速道路を利用したか」の情報を持っている
- HighwayDrive: 1ドライバーの、1回の高速道路の走行記録
- VehicleFamily: 車種
- AreaType: 高速道路のエリア
- DiscountPercentage: 割引率のパーセンテージ

### util パッケージ
- HolidayUtils: 休日の判定用。 `isHoliday(d: LocalDate): Boolean` と `printHolidays(): Unit` がpublic。
