# KATA01 - Discount Logic of Electronic Toll Collection System

高速道路の ETC 割引の計算ロジックを実装します。

- 走行記録は、24 時間を超えないものとします。
- 複数の割引が適用できる場合、 **最も割引率が高い割引が 1 つだけ** 適用されます。

## 業務ルール

割引には以下の適用要件があります。

### 割引1. 平日朝割引

#### 割引適用の条件
- 「平日」の「6時〜9時」に、入口または出口料金所を通過すること
- 「地方部」の走行であること

#### 割引率
当月の利用回数が
- 5回〜9回の場合: 30%割引
- 10回以上の場合: 50%割引

### 割引2. 休日割引

#### 割引適用の条件
- 車種が 「普通車」「軽自動車」「二輪車」のいずれかであること
- 「休日」に、入口または出口料金所を通過すること
- 「地方部」の走行であること

#### 割引率
- 30%割引

### 割引3. 深夜割引

#### 割引適用の条件
- 「0〜４時」の走行を含むこと

#### 割引率
- 30%割引

## 問題

上記の業務ルールに従って、割引率を計算する `DiscountCalculator` を、

- `DiscountCalculatorTest` をパスするように
- なるべく **割引のパターンが増えたとしても理解しやすく、メンテナンスが楽なように**

実装してください。



```scala
class DiscountCalculator {
    def calc(drive: HighwayDrive): DiscountPercentage = ???
}
```

class や trait、object などを新しく作ったり、既存の model にメソッドを新しく追加して OK です。

## 既存のコードについて

- DiscountCalculator: これを実装する

### model パッケージ

- HighwayDrive: 1 ドライバーの、 1 回の高速道路の走行記録。これを元に割引率を計算する。
  - Driver: ドライバー。「今月に何回高速道路を利用したか」の情報を持っている
  - VehicleFamily: 車種
    - Standard: 普通車
    - Mini: 軽自動車
    - Motorcycle: 二輪車
    - Other: その他
  - AreaType: 高速道路のエリア
    - Urban: 都市部（東京・大阪近郊）
    - Rural: 地方部

- DiscountPercentage: 割引率のパーセンテージ

### util パッケージ

- HolidayUtils: 休日の判定用。 `isHoliday(d: LocalDate): Boolean` と `printHolidays(): Unit` が public。

## FAQ

### 「X時〜Y時に、入口または出口料金所を通過すること」はどう判定する？
`HighwayDrive` の `enteredAt (入口料金所の通過時間)` や `exitedAt（出口料金所の通過時間)` からいい感じに判定してください。

### 2つの期間が重なり合うかどうかを判定するには？

2つの期間 `A〜B` と `X〜Y` が重なっているかどうかを判定したい場合は、

```
X <= B && A <= Y
```

という条件式で判定ができます。
詳細は [2 つの期間が重なり合うかどうかを判定する。](https://koseki.hatenablog.com/entry/20111021/range) を。
