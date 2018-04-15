(ns parser (:require [clojure.string :as string]))
  
(defn get-counter-signal-name [rules] ;Retorna el nombre del contador o señal
  (if (is-a-counter rules)
    (nth rules 1)
    (subs (string/join " " (nth rules 1)) 2  (- (string/index-of (nth rules 1) " " ) 1))
  )
)

(defn get-counter-params [rules] ;Devuelve los parametros del contador
  (nth rules 2)
)

(defn get-signal-operation [rules] ;Devuelve la operacion para obtener el valor de la señal
  (get (nth rules 1) (get-rule-name rules))
)

(defn get-rule-condition [rules] ;Retorna la condicion de la regla
  (last rules)
)
(defn is-a-counter [rules] ;Evalua si la regla es un contador
  (= "define-counter" (str(nth rules 0)))
)

(defn is-a-signal [rules] ;Evalua si la regla es una señal
  (= "define-signal" (str(nth rules 0)))
)
