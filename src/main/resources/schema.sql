-- タスクテーブル
CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_no VARCHAR(20) NOT NULL,
    task_name VARCHAR(100) NOT NULL,
    registration_date DATETIME NOT NULL,
    registrant VARCHAR(50) NOT NULL,
    detail TEXT,
    task_status VARCHAR(20) NOT NULL
);

-- 社員情報テーブル
CREATE TABLE IF NOT EXISTS employee_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_no VARCHAR(20) NOT NULL,         -- 各タスクに紐づく
    employee_department VARCHAR(50),
    employee_project VARCHAR(50),
    employee_number INT,
    employee_name VARCHAR(50),
    answer_status VARCHAR(20) DEFAULT '未回答',
    answer_remark TEXT,
    -- 同じ (task_no, employee_number) の組み合わせが登録されないようにユニーク制約を付与
    UNIQUE KEY uniq_employee_number (employee_number)
);
