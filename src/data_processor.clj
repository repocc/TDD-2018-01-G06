(ns data-processor)

(def rules '((define-counter "email-count" [] true)
             (define-counter "spam-count" [] (current "spam"))
             (define-signal {"spam-fraction" (/ (counter-value "spam-count" []) (counter-value "email-count" []))} true)
             (define-counter "spam-important-table" [(current "spam") 
                                                     (current "important")] true)))

(defn initialize-processor [rules]
  (for [lst rules]
      (if (= "define-counter" (str (first lst)))
          (define-rule-counter (rest lst))
          (if (= "define-signal" (str (first lst)))
            (define-rule-signal (rest lst))
            (println " ")
          )
      )
  )
)

(defn define-rule-counter [data]
  (def name-counter (first data))
  (def args (nth data 1))
  (def condition (last data))
  
  (define-counter name-counter args condition)
)

(defn define-rule-signal [data]
  (def signal (first data))
  (def condition (last data))
  
  (define-signal signal condition)
)
         
(defn process-data [state new-data] [nil []]

)
         
(defn query-counter [state counter-name counter-args] 0

)
