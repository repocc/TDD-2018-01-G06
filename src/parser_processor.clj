(ns parser
  (:require [clojure.string :as string]))

(defn is-a-counter [rules]   "Returns true if the rule is a counter"
  (= "define-counter" (str(nth rules 0)))
)

(defn is-a-signal [rules] "Returns true if the rule is a signal"
  (= "define-signal" (str(nth rules 0)))
)
  
(defn get-rule-name [rules] "Returns the name of the rule"
  (if (is-a-counter rules)
    (nth rules 1)
    (subs (string/join " " (nth rules 1)) 2  (- (string/index-of (nth rules 1) " " ) 1))
  )
)

(defn get-counter-params [rules] "Returns the parameters of the counter"
  (nth rules 2)
)

(defn get-signal-result [rules] "Returns the operation to get the result of the signal"
  (get (nth rules 1) (get-rule-name rules))
)

(defn get-rule-condition [rules] "Returns the condition of the rule"
  (last rules)
)
