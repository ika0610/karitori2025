-- タスクテーブルの作成
CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_no VARCHAR(20) NOT NULL,
    task_name VARCHAR(100) NOT NULL,
    registration_date DATETIME NOT NULL,
    registrant VARCHAR(50) NOT NULL,
    detail TEXT,
    task_status VARCHAR(20) NOT NULL,
    UNIQUE KEY uniq_task_no (task_no)  -- 同じタスク番号の重複を防ぐ
);

-- 社員情報テーブルの作成
CREATE TABLE IF NOT EXISTS employee_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_no VARCHAR(20) NOT NULL,  -- 各タスクに紐づく
    employee_department VARCHAR(50),
    employee_project VARCHAR(50),
    employee_number INT,
    employee_name VARCHAR(50),
    answer_status VARCHAR(20) DEFAULT '未回答',
    answer_remark TEXT,
    -- タスク番号と社員番号の組み合わせで重複を防ぐ
    UNIQUE KEY uniq_task_emp (task_no, employee_number)
);
