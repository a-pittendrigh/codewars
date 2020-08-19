(ns bookseller.core)
;; kata https://www.codewars.com/kata/54dc6f5a224c26032800005c/train/clojure
(defn stock-list [list-of-books list-of-cat]
  (if (or (= [] list-of-books) (= [] list-of-cat))
      []
      (let [wanted-category-totals (set list-of-cat)
            initial-category-totals (mapv (fn [category] (str category " 0")) list-of-cat)
            ordered-category-totals (into {} (map #(clojure.string/split % #" ") initial-category-totals))]
        (->> list-of-books
             (into initial-category-totals)
             (map #(clojure.string/split % #" "))
             (group-by #(first (first %)))
             (mapv (fn [[cat books]]
                     [(str cat)
                      (reduce (fn [sum [_ qty]] (+ sum (Integer/parseInt qty))) 0 books)]))
             (filter (fn [[cat _]] (contains? wanted-category-totals cat)))
             (into {})
             (merge ordered-category-totals)
             (into [])))))

(comment
  (let [ur ["BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600" "W 500"]
        vr [ "B" "C" "D" "Z" "A"]
        vr []
        res [["B" 1290] ["C" 515] ["D" 600] ["A" 0]]]
    #_(= (stock-list ur vr) res)
    #_(->>
     (stock-list ur vr)
     (into {})
     (merge {"A" 10, "B" 1290, "C" 515, "D" 600, "Z" 0}))

    ;;
    (= [] [])
    (stock-list ur vr)
    )

         ( ->>
          (into {} [["U" 0] ["V" 0] ["R" 225]])
          (into []))
         )

