(ns bookseller.core)
;; kata https://www.codewars.com/kata/54dc6f5a224c26032800005c/train/clojure
(defn stock-list [list-of-books list-of-cat]
  (->> list-of-books
       (into (map (fn [category] (str category " 0")) list-of-cat))
       (map #(clojure.string/split % #" "))
       (group-by #(first (first %)))
       (mapv (fn [[cat books]]
               [(str cat)
                (reduce (fn [sum [_ qty]] (+ sum (Integer/parseInt qty))) 0 books)]))
       (sort-by first))
  )

(let [ur ["BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600"]
      vr ["A" "B" "C" "D"]
      res [["A" 0] ["B" 1290] ["C" 515] ["D" 600]]]
  (= (stock-list ur vr) res)
  #_(stock-list ur vr))

(comment
  (into (map
         (fn [category] (str category " 0"))
         ["A" "B" "C" "D"]) ["BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600"])


  (type \A)
  (= "A" (str \A))
  (str \A)

  (-> 1 (-> identity))
  (-> "1 1"(->> #" " (clojure.string/split)))
  (-> "1 1" (clojure.string/split #" "))
  (reduce (fn [k v] (prn v) (into k v)) {:a [] :b []})
  (prn "sadaskl")

  (reduce
   (fn [a b]
     (prn (second b)
          b)
     (into a (second b)))
   ;;#(+ %1 (second (Integer/parseInt %2)))
   []
   (parseInt "1")
   )
  
  (->> [["BBAR" "150"] ["BKWR" "250"] ["BTSQ" "890"]]
   (map second)
   (map #(Integer/parseInt %))
   (reduce +))
  (reduce )

  (juxt (fn [t] t) {:a [1 2 3] :b [1 2 3]})
  )



;; (ns bookseller.core-test
;;   (:require [clojure.test :refer :all]
;;             [bookseller.core :refer :all]))

(comment
  (deftest a-test1
    (testing "Test 1"
      (def ur ["BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600"])
      (def vr ["A" "B" "C" "D"])
      (def res [["A" 0] ["B" 1290] ["C" 515] ["D" 600]])
      (is (= (stock-list ur vr) res)))))
