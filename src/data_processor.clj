(ns data-processor)

(def rules '((define-counter "email-count" [] true)
             (define-counter "spam-count" [] (current "spam"))
             (define-signal {"spam-fraction" (/ (counter-value "spam-count" []) (counter-value "email-count" []))} true)
             (define-counter "spam-important-table" [(current "spam") 
                                                     (current "important")] true)))

(defn initialize-processor [rules]

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

(def functions {"=" = "+" + "-" - "*" * "/" / "mod" mod "<" < ">" > "<=" <= ">=" >= "concat" str})

(defn get-function [function]
  ;Toma el operador pasado como parametro y llama a la funcion correspondiente
 (get functions function)
)

(defn apply-operator [function param1 param2]
  ;Aplica la funcion correspondiente del operator a los parametros
(apply (get-function function) [param1 param2])
)

